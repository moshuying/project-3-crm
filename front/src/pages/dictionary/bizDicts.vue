<template>
<div>
  <a-card>
    <div>
      <a-space class="operator">
        <a-form layout="inline" :form="queryForm">
          <a-form-item label="关键字">
            <a-input
                v-decorator="['keyword', { rules: [{ required: false,min:1,max:120,message:'输入长度应在1到120之间'}] }]"
                placeholder="请输入关键字"
            />
          </a-form-item>
          <a-form-item>
            <a-button @click="query()" :loading="queryLoading">查询</a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="showModal('添加')">添加业务表单</a-button>
          </a-form-item>
          <a-form-item>
            <a-button  icon="redo"  @click="fetch()"></a-button>
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
           <span slot="action" slot-scope="text">
              <a @click="updateItem(text.id)">编辑</a>
           </span>
      </a-table>
    </div>
  </a-card>
</div>
</template>

<script>
import * as dictionaryBizRef from "@/services/dictionaryBizRef";

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '名称',
    dataIndex: 'bizName',
    ellipsis: true,
  },
  {
    title: '对应关系',
    dataIndex: 'tableName',
    ellipsis: true,
  },
  {
    title: '字段明细',
    dataIndex: 'schemaJson',
    ellipsis: true,
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]
export default {
  /**
   * @name: bizDicts (业务字典集合)
   * @author: Administrator
   * @date: 2023/5/23 11:16
   * @description：bizDicts
   * @update: 2023/5/23 11:16
   */
  name: "bizDicts",

  data(){
    return {
      pagination: {},
      loading: false,
      columns: columns,
      dataSource: [],
      selectedRows: [],
    }
  },

  methods:{
    fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      dictionaryBizRef.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.records.map((e, i) => ({key: i + "",...e}))
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
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

  },

  created() {
    this.fetch()
  }
}
</script>

<style scoped>
.operator {
  margin-bottom: 18px;
}
</style>