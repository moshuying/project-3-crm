<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false,min:1,max:120,message:'内容长度在1到120之间'}] }]"
                  placeholder="请输入姓名/电话"
              />
            </a-form-item>
            <a-form-item label="跟进时间">
              <a-range-picker
                  :show-time="{ format: 'HH:mm' }"
                  v-decorator="['rangeTime', { rules: [{ required: false}] }]"
                  format="YYYY/MM/DD HH:mm:ss"
                  :placeholder="['开始时间', '结束时间']"
              />
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
  </div>
</template>

<script>
import * as customerHandover from "@/services/customerHandover"
import moment from "moment";

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '客户姓名',
    dataIndex: 'customerName',
  },
  {
    title: '操作日期',
    dataIndex: 'transTime',
    customRender:(text)=>moment(text).format("YYYY-MM-DD")
  },
  {
    title: '操作人',
    dataIndex: 'transUser',
  },
  {
    title: '旧营销人员',
    dataIndex: 'oldSeller',
  },
  {
    title: '新营销人员',
    dataIndex: 'newSeller',
  },
  {
    title: '移交原因',
    dataIndex: 'transReason',
  }
]
export default {
  data() {
    return {
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
      // 员工表
    }
  },
  async mounted() {
    await this.query()
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
      customerHandover.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.list.map((e, i) => ({
          ...e,
          key: i + "",
        }))
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
