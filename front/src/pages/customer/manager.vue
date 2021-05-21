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
          <a-button type="primary" @click="showModal('新增')">添加</a-button>
        </a-space>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
        >
           <span slot="action" slot-scope="text">
             <a-button type="link" shape="round" icon="edit" size="small" @click="updateItem(text.id)" >编辑</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" >跟进</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" >移交</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showStatusModal(text.id)" >修改状态</a-button>
           </span>
        </a-table>
      </div>
    </a-card>
<!--    新增修改-->
    <a-modal
        :title="title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        okText="提交"
    >
      <a-form :form="form" :layout="`horizontal`">
          <a-form-item hidden>
            <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
          </a-form-item>
        <div v-for="(item) in baseColumns" :key="item.dataIndex">
          <a-form-item :label="item.title">
            <a-select v-if="item.dataIndex==='gender'"
                style="width: 6rem"
                v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">
              <a-select-option :value="1">
                男
              </a-select-option>
              <a-select-option :value="0">
                女
              </a-select-option>
            </a-select>
            <a-select v-else-if="item.dataIndex==='job'" v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">
              <a-select-option v-for="(list) in dictionaryDetailsJob" :key="list.id">
                {{list.title}}
              </a-select-option>
            </a-select>
            <a-select v-else-if="item.dataIndex==='source'" v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">
              <a-select-option v-for="(list) in dictionaryDetailsSource" :key="list.id">
                {{list.title}}
              </a-select-option>
            </a-select>
            <a-input v-else
                v-decorator="[item.dataIndex, { rules: [{ required: true, message: item.title  }]}]"
                :placeholder="`请输入`+item.title"
            />
          </a-form-item>
        </div>
      </a-form>
    </a-modal>
    <a-modal
        title="修改客户状态"
        :visible="statusVisible"
        :confirm-loading="statusConfirmLoading"
        @ok="handleStatusOk"
        @cancel="handleStatusCancel"
        okText="保存"
    >
      <a-form :form="statusForm" :layout="`horizontal`">
        <a-form-item hidden>
          <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item lable="姓名">
          <a-input
               v-decorator="['name', { rules: [{ required: true, message: '姓名'  }]}]"
               :placeholder="`请输入姓名`"
          />
        </a-form-item>
        <a-form-item label="状态">
          <a-select
              v-decorator="['status',{ rules: [{ required: true, message: '状态' }] }]">
            <a-select-option
                :value="key"
                :key="index"
                v-for="(value,key,index) in statusMap">
              {{value}}
            </a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import * as customerManager from "@/services/customerManager"
import * as dictionaryDetails from "@/services/dictionaryDetails"
const statusMap = {
  "-2": "流失",
  "-1": "开发失败",
  "0": "潜在客户",
  "1": "正式客户",
  "2": "资源池客户",
}
const baseColumns =[
  {
    title: '姓名',
    dataIndex: 'name',
  },
  {
    title: '年龄',
    dataIndex: 'age',
  },
  {
    title: '性别',
    dataIndex: 'gender',
    customRender:(text)=>text===1?'男':'女'
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
  }
]
const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
    ...baseColumns,
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
      statusMap,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
      // 新增修改
      baseColumns,
      form:this.$form.createForm(this, {name: 'coordinated'}),
      confirmLoading:false,
      title:'新增',
      visible: false,
      // 修改客户状态
      statusForm:this.$form.createForm(this, {name: 'coordinated'}),
      statusConfirmLoading:false,
      statusVisible:false,
      // 字典细节
      dictionaryDetailsJob:[],
      dictionaryDetailsSource:[]
    }
  },
  mounted() {
    this.queryForm.setFieldsValue({"status":"0"})
    this.query()
    // id 1 职业
    dictionaryDetails.list({page:1,size:999999,id:1}).then(({data})=>{
      this.dictionaryDetailsJob = data.data.list
    });
    // id 2 来源
    dictionaryDetails.list({page:1,size:999999,id:2}).then(({data})=>{
      this.dictionaryDetailsSource = data.data.list
    })

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
    // 客户状态
    handleStatusOk(){
      this.statusConfirmLoading = true;
      this.statusForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.statusConfirmLoading = false
          return;
        }
        values.status = parseInt(values.status)
        customerManager['update'](values).then(({data}) => {
          this.statusConfirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '角色信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }
          this.$notification.success({
            message: this.title + '成功',
            description: this.title + '角色信息成功',
          });
          this.statusVisible = false
          this.fetch({"page": this.pagination.current, "size": 10})
        })
      });
    },
    handleStatusCancel(){
      this.statusVisible = false;
      this.statusConfirmLoading = false
      this.statusForm.resetFields()
    },
    async showStatusModal(id){
      this.statusVisible = true;
      await this.statusForm.resetFields()
      const {data} = await customerManager.getDetail(id)
      if(!data.data) return;
      // 这里不能循环
      this.statusForm.setFieldsValue({id:data.data["id"]})
      this.statusForm.setFieldsValue({name:data.data["name"]})
      this.statusForm.setFieldsValue({status:data.data["status"]+""})
    },
    // modal
    async showModal(title) {
      this.visible = true;
      this.title = title || '新增'
      await this.form.resetFields()
    },
    async updateItem(id) {
      this.confirmLoading=false
      await this.showModal('更改')
      customerManager.getDetail(id).then(({data}) => {
        if(!data.data) return;
        // 这里不能循环
        this.form.setFieldsValue({id:data.data["id"]})
        this.form.setFieldsValue({name:data.data["name"]})
        this.form.setFieldsValue({age:data.data["age"]})
        this.form.setFieldsValue({gender:data.data["gender"]})
        this.form.setFieldsValue({tel:data.data["tel"]})
        this.form.setFieldsValue({qq:data.data["qq"]})
        this.form.setFieldsValue({job:data.data["job"]})
        this.form.setFieldsValue({source:data.data["source"]})
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

        customerManager[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '角色信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }
          this.$notification.success({
            message: this.title + '成功',
            description: this.title + '角色信息成功',
          });
          this.visible = false
          this.fetch({"page": this.pagination.current, "size": 10})
        })
      });
    },
    handleCancel() {
      this.visible = false;
      this.title = ''
      this.confirmLoading = false
      this.form.resetFields()
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
