<template>
  <div>
    <a-card>
      <div style="width: 100%">
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['name', { rules: [{ required: false,min:1,max:120,message:'输入长度应在1到120之间'}] }]"
                  placeholder="请输入姓名"
              />
            </a-form-item>
            <a-form-item label="时间段查询">
              <a-range-picker
                  :show-time="{ format: 'HH:mm' }"
                  v-decorator="['rangeTime', { rules: [{ required: false}] }]"
                  format="YYYY/MM/DD HH:mm:ss"
                  :placeholder="['开始时间', '结束时间']"
              />
            </a-form-item>
            <a-form-item label="分组类型">
              <a-select
                  style="width: 8rem"
                  v-decorator="['groupType', { rules: [{ required: false}] }]">
                <a-select-option
                    :key="index"
                    :value="key"
                    v-for="(value,key,index) in groupType">
                  {{ value }}
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item>
              <a-button @click="query()" :loading="queryLoading">查询</a-button>
            </a-form-item>
          </a-form>
        </a-space>
      </div>
        <a-space>
          <a-button type="primary" @click="showCharts()">柱状图</a-button>
          <a-button type="primary" @click="showChartsPie()">饼状图</a-button>
        </a-space>
      <p></p>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
        >
        </a-table>
    </a-card>
    <a-modal
        :title="title"
        @cancel="handleChartCancel"
        width="100%"
        centered
        :footer="null"
        :visible="chartsVisible">
      <div :style="{width: '100%', height:'90vh' }" ref="chart" />
    </a-modal>
  </div>
</template>

<script>
import * as analysis from "@/services/analysis"
import * as Echarts from "echarts"
import validators from "@/utils/validators";
const groupType = {
  "1": '员工',
  "2": '年',
  "3": '月',
  "4": '日',
}
const columns = [
  {
    title: '分组类型',
    dataIndex: 'name'
  },
  {
    title: '潜在客户新增数',
    dataIndex: 'count',
  }
]
export default {
  data() {
    return {
      validators,
      title:'',
      chartsVisible:false,
      queryForm: this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading: false,
      groupType,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
    }
  },
  async mounted() {
    this.queryForm.setFieldsValue({"groupType": "1"})
    this.fetch()
  },
  methods: {
    showCharts(){
      this.chartsVisible = true
      this.init()
      this.title = "潜在客户报表-柱状图"
    },
    handleChartCancel(){
      this.chartsVisible = false
      Echarts.dispose(this.$refs.chart)
      window.removeEventListener("resize",this.listenHandal,false)
    },
    showChartsPie(){
      this.chartsVisible = true
      this.init2()
      this.title = "潜在客户报表-饼状图"
    },
    init2(){
      this.$nextTick(async ()=>{
        //2.初始化
        this.chart = Echarts.init(this.$refs.chart);
        const data =(await this.query(999999999)).data.list
        //3.配置数据
        let option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '50%',
              data: data.map(e=>({value:e.count,name:e.name})),
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        // 4.传入数据
        this.chart.setOption(option);
        window.addEventListener('resize', this.listenHandal,false);
      })
    },
    listenHandal(){
        this.chart.resize();
    },
    init() {
      this.$nextTick(async ()=>{
        //2.初始化
        this.chart = Echarts.init(this.$refs.chart);
        const data =(await this.query(999999999)).data.list
        //3.配置数据
        let option = {
          xAxis: { type: 'category', data: data.map(e=>e.name) }, //X轴
          yAxis: { type: 'value' }, //Y轴
          series: [{ data: data.map(e=>e.count), type: 'bar' }] //配置项
        };
        // 4.传入数据
        this.chart.setOption(option);
        window.addEventListener('resize', this.listenHandal,false);
      })
    },
    async query(size) {
      return new Promise((resolve) => {
        this.queryLoading = true
        this.queryForm.validateFields(async (err, values) => {
          if (err) {
            console.log("form error");
            this.queryLoading = false
            return;
          }
          if (values.rangeTime) {
            if (values.rangeTime.length !== 0) {
              values.startTime = values.rangeTime[0].toDate().toISOString()
              values.endTime = values.rangeTime[1].toDate().toISOString()
            }
            delete values.rangeTime
          }
          values.groupType = parseInt(values.groupType)
          let res = await this.fetch({"page": 1, "size": size||10, ...values})
          resolve(res)
        })
      })
    },
    // table
    handleTableChange(pagination) {
      const pager = {...this.pagination};
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        size: pagination.pageSize,
        page: pagination.current,
      });
    },
    async fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      const {data} =await analysis.list(params || {"page": 1, "size": 10})
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.list.map((e, i) => ({key: i + "", ...e}))
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      return data
    }
  }
}
</script>

<style lang="less" scoped>
.ant-modal-content{
  width: 100%;
  height: 100%;
}
.search {
  margin-bottom: 54px;
}

.fold {
  width: calc(100% - 216px);
  display: inline-block
}

.operator {
  margin-bottom: 18px;
}

@media screen and (max-width: 900px) {
  .fold {
    width: 100%;
  }
}
</style>
