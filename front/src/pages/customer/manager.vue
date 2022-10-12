<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false,min:1,max:120,message:'输入长度应在1到120之间'}] }]"
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
            :scroll="{ x: 1500, y: 300 }"
        >
           <span slot="action" slot-scope="text">
             <a-button type="link" shape="round" icon="edit" size="small" @click="updateItem(text.id,text)" >编辑</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showFollowModal(text.id,text)" >跟进</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showHandoverModal(text.id)">移交</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showStatusModal(text.id)" >修改状态</a-button>
           </span>
        </a-table>
      </div>
    </a-card>
<!--    新增修改-->
<!--    <a-modal-->
<!--        :title="title"-->
<!--        :visible="visible"-->
<!--        :confirm-loading="confirmLoading"-->
<!--        @ok="handleOk"-->
<!--        @cancel="handleCancel"-->
<!--        okText="提交"-->
<!--    >-->
<!--      <a-form :form="form" :layout="`horizontal`">-->
<!--          <a-form-item hidden>-->
<!--            <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>-->
<!--          </a-form-item>-->
<!--        <div v-for="(item) in baseColumns" :key="item.dataIndex">-->
<!--          <a-form-item :label="item.title">-->
<!--            <a-select v-if="item.dataIndex==='gender'"-->
<!--                style="width: 6rem"-->
<!--                v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">-->
<!--              <a-select-option :value="1">-->
<!--                男-->
<!--              </a-select-option>-->
<!--              <a-select-option :value="0">-->
<!--                女-->
<!--              </a-select-option>-->
<!--            </a-select>-->
<!--            <a-select v-else-if="item.dataIndex==='job'" v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">-->
<!--              <a-select-option v-for="(list) in dictionaryDetailsJob" :key="list.id">-->
<!--                {{list.title}}-->
<!--              </a-select-option>-->
<!--            </a-select>-->
<!--            <a-select v-else-if="item.dataIndex==='source'" v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">-->
<!--              <a-select-option v-for="(list) in dictionaryDetailsSource" :key="list.id">-->
<!--                {{list.title}}-->
<!--              </a-select-option>-->
<!--            </a-select>-->
<!--            <a-input-number v-else-if="item.dataIndex==='age'" :min="0" :max="200" v-decorator="[item.dataIndex, { rules: [{ required: true, message: item.title  }]}]" />-->
<!--            <a-input v-else-if="item.dataIndex==='name'"-->
<!--                     v-decorator="[item.dataIndex, { rules: [{ required: true,min:1,max:120,message:'输入长度应在1到120之间' }]}]"-->
<!--                     :placeholder="`请输入`+item.title"-->
<!--            />-->
<!--            <a-input v-else-if="item.dataIndex==='tel'"-->
<!--                     v-decorator="[item.dataIndex, { rules: [{ required: true,pattern:validators.phoneReg,message:validators.phoneMsg  }]}]"-->
<!--                     :placeholder="`请输入`+item.title"-->
<!--            />-->
<!--            <a-input v-else-if="item.dataIndex==='qq'"-->
<!--                     v-decorator="[item.dataIndex, { rules: [{ required: true,pattern:validators.qqReg,message:validators.qqMsg  }]}]"-->
<!--                     :placeholder="`请输入`+item.title"-->
<!--            />-->
<!--            <a-input v-else-->
<!--                v-decorator="[item.dataIndex, { rules: [{ required: true, message: item.title  }]}]"-->
<!--                :placeholder="`请输入`+item.title"-->
<!--            />-->
<!--          </a-form-item>-->
<!--        </div>-->
<!--      </a-form>-->
<!--    </a-modal>-->

    <add-customer :visible="visible" :baseColumns="baseColumns" ref="addModal"></add-customer>

<!--    修改客户状态-->
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
              disabled
               v-decorator="['name', { rules: [{ required: true, message: '名字长度在1到15之间',min:1,max:15  }]}]"
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
<!--    移交-->
    <a-modal
        title="移交"
        :visible="handoverVisible"
        :confirm-loading="handoverConfirmLoading"
        @ok="handleHandoverOk"
        @cancel="handleHandoverCancel"
        okText="保存">
      <a-form :form="handoverForm" :layout="`horizontal`">
        <a-form-item hidden>
          <a-input v-decorator="['oldseller',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item hidden>
          <a-input v-decorator="['customerid',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item hidden>
          <a-input v-decorator="['transuser',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item lable="客户姓名">
          <a-input
              disabled
              v-decorator="['name', { rules: [{ required: true, message: '姓名' }]}]"
          />
        </a-form-item>
        <a-form-item label="旧营销人员">
          <a-input
              disabled
              v-decorator="['oldsellerName',{ rules: [{ required: true, message: '旧营销人员' }] }]"
          />
        </a-form-item>
        <a-form-item label="新营销人员">
          <a-select
              v-decorator="['newseller',{ rules: [{ required: true, message: '状态' }] }]">
            <a-select-option
                :value="key"
                :key="index"
                v-for="(value,key,index) in employeeList">
              {{value.name}}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="移交原因">
          <a-textarea
              v-decorator="['transreason',{ rules: [{ required: true, message: '移交原因的内容长度在10和120之间' ,min:10,max:120 }] }]"
              :auto-size="{ minRows: 3, maxRows: 5 }"
          />
        </a-form-item>
      </a-form>
    </a-modal>
<!--    跟进-->
    <a-modal
        title="跟进记录"
        :visible="followVisible"
        :confirm-loading="followConfirmLoading"
        @ok="handleFollowOk"
        @cancel="handleFollowCancel"
        okText="保存">
      <a-form :form="followForm" :layout="`horizontal`">
        <a-form-item hidden>
          <a-input v-decorator="['customerid',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item disabled label="客户姓名">
          <a-input
              v-decorator="['name', { rules: [{ required: true, message: '姓名'  }]}]"
              disabled />
        </a-form-item>
        <a-form-item disabled label="跟进时间">
          <a-date-picker
              show-time
              v-decorator="['tracetime', { rules: [{ required: true, message: '跟进时间'  }]}]"
              />
        </a-form-item><a-form-item disabled label="跟进内容">
          <a-input
              v-decorator="['tracedetails', { rules: [{ required: true, message: '跟进内容',min:1,max:120,message:'跟进内容长度在1到120之间'  }]}]"
              />
        </a-form-item>
        <a-form-item disabled label="跟进方式">
          <a-select
              v-decorator="['tracetype', { rules: [{ required: true, message: '跟进方式'  }]}]">
            <a-select-option
                :value="value.id"
                :key="index"
                v-for="(value,key,index) in dictionaryDetailsFollow">
              {{value.title}}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item disabled label="跟进结果">
          <a-select
              v-decorator="['traceresult', { rules: [{ required: true, message: '跟进结果'  }]}]">
            <a-select-option
                :value="key"
                :key="index"
                v-for="(value,key,index) in followTraceResult">
              {{value}}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item disabled label="备注">
          <a-textarea
              v-decorator="['comment', { rules: [{ required: true, message: '备注' ,min:1,max:120,message:'备注内容在1到120之间'}]}]"
              :auto-size="{ minRows: 3, maxRows: 5 }"
              />
        </a-form-item>
        <a-form-item disabled label="跟进类型">
          <a-select
              v-decorator="['type', { rules: [{ required: true, message: '跟进类型'  }]}]"
              >
            <a-select-option
                :value="key"
                :key="index"
                v-for="(value,key,index) in followType">
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
import * as employee from "@/services/employee"
import * as customerHandover from "@/services/customerHandover"
import * as customerFollowUpHistory from "@/services/customerFollowUpHistory"
import moment from "moment";
import validators from "@/utils/validators";
import AddCustomer from "@/pages/customer/modal/AddCustomer";

const baseColumns =[
  {
    width:120,
    title: '姓名',
    dataIndex: 'name',
    ellipsis: true,
    fixed: 'left'
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
    ellipsis: true,
  },
  {
    width:60,
    title: '来源',
    dataIndex: 'source',
    ellipsis: true,
  }
]
const columns = [
  {
    width:60,
    title: '编号',
    dataIndex: 'id',
    fixed: 'left'
  },
    ...baseColumns,
  {
    title: '营销人员',
    dataIndex: 'inputuser'
  },
  {
    title: '状态',
    dataIndex: 'status',
    customRender:(text)=>customerManager.statusMap[parseInt(text)]
  },
  {
    width:380,
    title: '操作',
    scopedSlots: {customRender: 'action'},
    fixed: 'right'
  }
]

export default {
  name: 'Department',
  components: {AddCustomer},
  data() {
    return {
      validators,
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
      statusMap:customerManager.statusMap,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {current:1},
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
      // 移交
      handoverForm:this.$form.createForm(this, {name: 'coordinated'}),
      handoverVisible :false,
      handoverConfirmLoading :false,
      // 跟进
      followForm:this.$form.createForm(this, {name: 'coordinated'}),
      followVisible :false,
      followConfirmLoading :false,
      followType:customerFollowUpHistory.type,
      followTraceResult:customerFollowUpHistory.traceresult,
      // 字典细节
      dictionaryDetailsJob:[],
      dictionaryDetailsSource:[],
      dictionaryDetailsFollow:[],
      // 员工表
      employeeList:[]
    }
  },
  mounted() {
    this.queryForm.setFieldsValue({"status":"0"})
    this.query()
    // id 1 职业
    dictionaryDetails.list({page:1,size:999999,id:1}).then(({data})=>{
      this.dictionaryDetailsJob = data.data.records
    });
    // id 2 来源
    dictionaryDetails.list({page:1,size:999999,id:2}).then(({data})=>{
      this.dictionaryDetailsSource = data.data.records
    })
    // id 10 跟进方式
    dictionaryDetails.list({page:1,size:999999,id:10}).then(({data})=>{
      this.dictionaryDetailsFollow = data.data.records
    })

    employee.list({page:1,size:99999}).then(({data})=>{
      this.employeeList = data.data.records
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
        this.fetch({"page": this.pagination.current, "size": 10,...values})
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
      this.dataSource = res.records.map((e, i) => ({key: i + "", ...e}))
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
          }else {
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '角色信息成功',
            });
          }
          this.statusVisible = false
          this.query()
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
      this.statusConfirmLoading = false
      await this.statusForm.resetFields()
      const {data} = await customerManager.getDetail(id)
      if(!data.data) return;
      // 这里不能循环
      this.statusForm.setFieldsValue({id:data.data["id"]})
      this.statusForm.setFieldsValue({name:data.data["name"]})
      this.statusForm.setFieldsValue({status:data.data["status"]+""})
    },
    // 移交
    handleHandoverCancel(){
      this.handoverVisible = false;
      this.handoverConfirmLoading = false
      this.handoverForm.resetFields()
    },
    handleHandoverOk(){
      this.handoverConfirmLoading = true;
      this.handoverForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.handoverConfirmLoading = false
          return;
        }
        customerHandover['add'](values).then(({data}) => {
          this.handoverConfirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '角色信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '角色信息成功',
            });
          }
          this.handoverVisible = false
          this.query()
        })
      });
    },
    async showHandoverModal(id){
      this.handoverVisible = true;
      this.handoverConfirmLoading = false
      await this.handoverForm.resetFields()
      const {data} = await customerManager.getDetail(id)
      if(!data.data) return;
      // 这里不能循环
      this.handoverForm.setFieldsValue({oldsellerName:this.employeeList.find(e=>e.id===data.data["inputuser"]).name})
      this.handoverForm.setFieldsValue({oldseller:data.data["inputuser"]})
      this.handoverForm.setFieldsValue({customerid:data.data["id"]})
      this.handoverForm.setFieldsValue({name:data.data["name"]})
    },
    // 跟进
    handleFollowCancel(){
      this.followVisible = false;
      this.followConfirmLoading = false
      this.followForm.resetFields()
    },
    handleFollowOk(){
      this.followConfirmLoading = true;
      this.followForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.followConfirmLoading = false
          return;
        }
        values.tracetime = values.tracetime.toDate()

        customerFollowUpHistory['add'](values).then(({data}) => {
          this.followConfirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: '跟进记录出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message: '成功',
              description: '跟进记录成功',
            });
          }
          this.followVisible = false
          this.query()
        })
      });
    },
    async showFollowModal(id,line){
      this.followVisible = true;
      this.followConfirmLoading = false
      await this.followForm.resetFields()
      // 这里不能循环
      this.$nextTick(()=>{
        this.followForm.setFieldsValue({customerid:line.id})
        this.followForm.setFieldsValue({name:line.name})
        this.followForm.setFieldsValue({tracetime:new moment(new Date())})
        this.followForm.setFieldsValue({tracetype:this.dictionaryDetailsFollow[0].id})
        this.followForm.setFieldsValue({type:Object.keys(this.followType)[0]})
        this.followForm.setFieldsValue({traceresult:Object.keys(this.followTraceResult)[0]})
      })
    },
    // modal
    async showModal(title) {
      this.visible = true;
      this.title = title || '新增'
      await this.form.resetFields()
    },
    async updateItem(id,line) {

      this.$refs["addModal"].updateItem1(id,line);
      // this.confirmLoading=false
      // await this.showModal('更改')
      // customerManager.getDetail(id).then(({data}) => {
      //   if(!data.data) return;
      //   // 这里不能循环
      //   this.form.setFieldsValue({id:data.data["id"]})
      //   this.form.setFieldsValue({name:data.data["name"]})
      //   this.form.setFieldsValue({age:line["age"]})
      //   this.form.setFieldsValue({gender:data.data["gender"]})
      //   this.form.setFieldsValue({tel:data.data["tel"]})
      //   this.form.setFieldsValue({qq:data.data["qq"]})
      //   this.form.setFieldsValue({job:data.data["job"]})
      //   this.form.setFieldsValue({source:data.data["source"]})
      // })
    },
    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.confirmLoading=false
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
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '角色信息成功',
            });
          }
          this.visible = false
          this.query()
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
