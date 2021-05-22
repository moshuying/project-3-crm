<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['name', { rules: [{ required: false}] }]"
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
                  {{value}}
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item>
              <a-button @click="query()" :loading="queryLoading">查询</a-button>
            </a-form-item>
          </a-form>
        </a-space>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
        >
        </a-table>
      </div>
    </a-card>
    <a-model>

    </a-model>
  </div>
</template>

<script>
import * as analysis from "@/services/analysis"

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
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
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
    this.queryForm.setFieldsValue({"groupType":"1"})
  },
  methods: {
    query(){
      this.queryLoading = true
      this.queryForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.queryLoading = false
          return;
        }
        if(values.rangeTime){
          if(values.rangeTime.length!==0){
            values.startTime=values.rangeTime[0].toDate().toISOString()
            values.endTime=values.rangeTime[1].toDate().toISOString()
          }
          delete values.rangeTime
        }
        values.groupType = parseInt(values.groupType)
        this.fetch({"page": 1, "size": 10,...values})
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
    fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      analysis.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.list.map((e, i) => ({key: i + "", ...e}))
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      })
    },
  }
}
</script>

<style lang="less" scoped>
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
