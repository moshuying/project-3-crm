<template>
  <a-card>
    <div>
      <a-space class="operator">
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
</template>

<script>
import * as department from "@/services/department"
// {
//   "id": 2,
//     "name": "测试部门",
//     "sn": "test deptarment"
// }
const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '部门名称',
    dataIndex: 'name',
  },
  {
    title: '部门编号',
    dataIndex: 'sn',
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]


export default {
  name: 'QueryList',
  data() {
    return {
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
    }
  },
  authorize: {
    deleteRecord: 'delete'
  },
  created() {
    this.fetch()
  },
  methods: {
    handleTableChange(pagination){
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        size: pagination.pageSize,
        page: pagination.current,
      });
    },
    fetch(params={
      "page": 1,
      "size": 10
    }){
      this.loading = true
      department.list(params).then(({data})=>{
        const res = data.data
        console.log("departmentList",res)
        const pagination = { ...this.pagination };
        console.log(res)
        pagination.total = res.total
        this.dataSource = res.list
        this.pagination = pagination
        this.loading = false
      })
    }
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
