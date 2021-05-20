<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false}] }]"
                  placeholder="请输入姓名/电话"
              />
            </a-form-item>
            <a-form-item label="状态">
              <a-select
                  style="width: 6rem"
                  v-decorator="['status',{ rules: [{ required: true, message: '状态' }] }]">
                <a-select-option
                    :value="key"
                    :key="index"
                    v-for="(value,key,index) in statusMap">
                  {{value}}
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item>
              <a-button :loading="queryLoading" @click="query()">查询</a-button>
            </a-form-item>
          </a-form>
          <a-button type="success" @click="showModal('新增')">添加</a-button>
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
import * as customerManager from "@/services/customerManager"

const statusMap = {
  "-2": "流失",
  "-1": "开发失败",
  "0": "潜在客户",
  "1": "正式客户",
  "2": "资源池客户",
}
const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '姓名',
    dataIndex: 'name',
  },
  {
    title: '年龄',
    dataIndex: 'age',
  },
  {
    title: '电话',
    dataIndex: 'tel',
  },
  {
    title: 'QQ',
    dataIndex: 'qq',
  },
  {
    title: '职业',
    dataIndex: 'job',
  },
  {
    title: '来源',
    dataIndex: 'source',
  },
  {
    title: '营销人员',
    dataIndex: 'inputuser',
  },
  {
    title: '状态',
    dataIndex: 'status',
    customRender:(text)=>statusMap[parseInt(text)]
  },
  {
    title: '部门',
    dataIndex: 'departmentName',
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]
export default {
  name: 'Department',
  data() {
    return {
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
      // table
      statusMap,
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
    }
  },
  created() {
    this.fetch()
  },
  mounted() {
    this.queryForm.setFieldsValue({"status":"潜在客户"})
  },
  methods: {
    query(){
      this.queryLoading = true
      this.queryForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          return;
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
    async fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      let {data} = await customerManager.list(params || {"page": 1, "size": 10})
      const res = data.data
      const pagination = {...this.pagination};
      pagination.total = res.total
      pagination.current = params.page
      this.dataSource = res.list.map((e, i) => ({key: i + "", ...e}))
      this.pagination = pagination
      this.loading = false
      this.queryLoading=false
      return data
    },
    deleteItem(text) {
      const title = '删除'
      customerManager.deleteItem(text.id).then(({data}) => {
        if (data.code !== 200) {
          this.$notification['error']({
            message: title + '客户信息出现错误',
            description: '建议检查网络连接或重新登陆',
          });
        }
        this.$notification.success({
          message: title + '成功',
          description: title + '客户信息成功',
        });
        this.fetch({"page": this.pagination.current, "size": 10})
      })
    },
    async updateItem(id) {
      if(this.confirmLoading){
        this.confirmLoading=false
      }
      this.targetKeys = []
      this.roleIds = []
      this.reloadId = id
      await this.showModal('更改')
      customerManager.getDetail(id).then(({data}) => {
        if(!data.data) return;
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"name": data.data["name"]})
        this.form.setFieldsValue({"admin": data.data["admin"] === 1})
        this.form.setFieldsValue({"age": data.data["age"]})
        this.form.setFieldsValue({"dept": data.data["dept"]})
        this.form.setFieldsValue({"email": data.data["email"]})
        this.form.setFieldsValue({"password": data.data["password"]})
        const {roleIds} = data.data
        if (!roleIds) return;
        for(let i=0;i<roleIds.length;i++){
          const find = this.roleIds.find(e=>roleIds[i]===(e.id+""))
          this.targetKeys.push(find.key)
        }
        console.log(this.targetKeys)
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
