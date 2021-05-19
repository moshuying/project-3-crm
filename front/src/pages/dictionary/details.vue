<template>
  <div style="display: flex;">
    <a-card style="width: 30%" title="数据字典分组">
      <div :key="item.id" v-for="(item) in dContentsList">
        <div
            :style="{backgroundColor:leftFirstId===item.id?'#337ab7':''}"
            @click="changeRight(item.id)">
          <a-button
              :style="{color:leftFirstId===item.id?'white':''}"
              type="link">{{item.title}}</a-button>
        </div>
      </div>
    </a-card>
    <a-card style="width: 100%">
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false}] }]"
                  placeholder="请输入姓名/邮箱"
              />
            </a-form-item>
            <a-form-item>
              <a-button @click="query()" :loading="queryLoading">查询</a-button>
            </a-form-item>
          </a-form>
          <a-button type="primary" @click="showModal('添加')">添加字典明细</a-button>
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
        <a-form-item hidden>
          <a-input v-decorator="['parentid',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item label="字典明细名称">
          <a-input
              v-decorator="['title', { rules: [{ required: true, message: '请输入字典明细名称' }] }]"
          />
        </a-form-item>
        <a-form-item label="字典明细序列">
          <a-input
              v-decorator="['sequence',{ rules: [{ required: true, message: '请输入字典明细序列' }] },]"
              placeholder="请输入字典明细序列"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import * as dictionaryDetails from "@/services/dictionaryDetails"
import * as dictionaryContents from "@/services/dictionaryContents"

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '名称',
    dataIndex: 'title',
  },
  {
    title: '序列',
    dataIndex: 'sequence',
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
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
      // modal
      title: '添加',
      visible: false,
      confirmLoading: false,
      dContentsList:[],
      // modal form
      form: this.$form.createForm(this, {name: 'coordinated'}),
      // left card
      leftFirstId:null,
    }
  },
  created() {
    dictionaryContents.list({page:1,size:999999}).then(({data})=>{
      this.dContentsList = data.data.list
      this.leftFirstId = this.dContentsList[0].id
      this.fetch({page:1,size:10,id:this.leftFirstId})
    })
  },
  methods: {
    changeRight(id){
      this.leftFirstId = id
      this.query()
    },
    query(){
      this.queryLoading = true
      this.queryForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          return;
        }
        this.fetch({"page": 1, "size": 10,id:this.leftFirstId,...values})
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
      dictionaryDetails.list(params || {"page": 1, "size": 10,id:this.leftFirstId}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.list.map((e, i) => ({key: i + "",...e}))
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      })
    },
    deleteItem(text) {
      const title = '删除'
      dictionaryDetails.deleteItem(text.id).then(({data})=>{
        if (data.code !== 200) {
          this.$notification['error']({
            message: title + '字典明细信息出现错误',
            description: '建议检查网络连接或重新登陆',
          });
        }
        this.$notification.success({
          message: title + '成功',
          description: title + '字典明细信息成功',
        });
        this.fetch({"page": this.pagination.current, "size": 10,id:this.leftFirstId})
      })
    },
    updateItem(id) {
      this.showModal('更改')
      dictionaryDetails.getDetail(id).then(({data}) => {
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"sequence": data.data["sequence"]})
        this.form.setFieldsValue({"title": data.data["title"]})
        this.form.setFieldsValue({"parentid": data.data["parentid"]})
      })
    },
    // modal
    showModal(title = '添加') {
      this.visible = true;
      this.title = title || '添加'
      this.form.resetFields()
      this.$nextTick(()=>{
        this.form.setFieldsValue({"parentid": this.leftFirstId})
      })
    },
    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {

        if (err) {
          console.log("form error");
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';
        dictionaryDetails[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '字典明细信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }
          this.$notification.success({
            message: this.title + '成功',
            description: this.title + '字典明细信息成功',
          });
          this.visible = false
          this.fetch({"page": this.pagination.current, "size": 10,id:this.leftFirstId})
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
