<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-button type="primary" @click="showModal('新增')">新增</a-button>
        </a-space>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
        >
           <span slot="action" slot-scope="text">
              <a @click="updateItem(text.id)">编辑</a> |
               <a-popconfirm
                   title="您真的要删除这行数据么？"
                   ok-text="是"
                   cancel-text="否"
                   @confirm="deleteItem(text)"
               >
              <a>删除</a>
              </a-popconfirm>
           </span>
        </a-table>
      </div>
    </a-card>
    <a-modal
        :title="title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        okText="提交"
    >
      <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
        <a-form-item hidden>
          <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item label="部门名称">
          <a-input
              v-decorator="['name', { rules: [{ required: true, min:1,max:15,message:'部门名称长度在1到15之间' }] }]"
          />
        </a-form-item>
        <a-form-item label="部门编号">
          <a-input
              v-decorator="['sn',{ rules: [{ required: true, min:1,max:30,message:'输入内容应在1到50位之间' }] },]"
              placeholder="请输入部门编号"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import * as department from "@/services/department"
import validators from "@/utils/validators";
const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '部门名称',
    dataIndex: 'name',
    ellipsis: true,
  },
  {
    title: '部门编号',
    dataIndex: 'sn',
    ellipsis: true,
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
      validators,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
      // modal
      title: '新增',
      visible: false,
      confirmLoading: false,
      // modal form
      form: this.$form.createForm(this, {name: 'coordinated'}),
    }
  },
  authorize: {
    deleteRecord: 'delete'
  },
  created() {
    this.fetch()
  },
  methods: {
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
      department.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.records.map((e, i) => ({key: i + "",...e}))
        this.pagination = pagination
        this.loading = false
      })
    },
    deleteItem(text) {
      const title = '删除'
      department.deleteItem(text.id).then(({data})=>{
        if (data.code !== 200) {
          this.$notification['error']({
            message: title + '部门信息出现错误',
            description: '建议检查网络连接或重新登陆',
          });
        }
        this.$notification.success({
          message: title + '成功',
          description: title + '部门信息成功',
        });
        this.fetch({"page": this.pagination.current, "size": 10})
      })
    },
    updateItem(id) {
      this.showModal('更改')
      department.getDetail(id).then(({data}) => {
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"sn": data.data["sn"]})
        this.form.setFieldsValue({"name": data.data["name"]})
      })
    },
    // modal
    showModal(title = '新增') {
      this.visible = true;
      this.title = title || '新增'
      this.form.resetFields()
    },
    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.confirmLoading = true;
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';

        department[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '部门信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '部门信息成功',
            });
          }
          this.visible = false
          this.fetch({"page": this.pagination.current, "size": 10})
        })
      });
    },
    handleCancel() {
      this.visible = false;
      this.title=''
      this.confirmLoading = false
      this.form.resetFields()
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
