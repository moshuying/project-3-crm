<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false,min:1,max:120,message:'内容长度在1到120之间'}] }]"
                  placeholder="请输入姓名或电话"
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
            <a-form-item label="跟进类型">
              <a-select
                  style="width: 8rem"
                  v-decorator="['type', { rules: [{ required: false}] }]">
                <a-select-option :value="9999999">
                  全部
                </a-select-option>
                <a-select-option
                    :key="index"
                    :value="key"
                    v-for="(value,key,index) in cfuhType">
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
           <span slot="action" slot-scope="text">
              <a @click="showFollowModal(text.id,text)">编辑</a>
           </span>
        </a-table>
      </div>
    </a-card>
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
          <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
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
            v-decorator="['tracedetails', { rules: [{ required: true, message: '跟进内容',min:1,max:120,message:'内容长度在1到120之间'  }]}]"
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
              v-decorator="['comment', { rules: [{ required: true, message: '备注',min:1,max:120,message:'内容长度在1到120之间'  }]}]"
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
import * as customerFollowUpHistory from "@/services/customerFollowUpHistory"
import * as employee from "@/services/employee"
import * as dictionaryDetails from "@/services/dictionaryDetails";
import moment from "moment";

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
    title: '跟进日期',
    dataIndex: 'tracetime',
    customRender:(text)=>moment(text).format("YYYY-MM-DD")
  },
  {
    title: '跟进内容',
    dataIndex: 'tracedetails',
  },
  {
    title: '跟进方式',
    dataIndex: 'tracetypeFind',
  },
  {
    title: '跟进结果',
    dataIndex: 'traceresult',
    customRender:(text)=>customerFollowUpHistory.traceresult[parseInt(text)]
  },
  {
    title: '录入人',
    dataIndex: 'inputuserFind',
  },
  {
    title: '跟进类型',
    dataIndex: 'type',
    customRender:(text)=>customerFollowUpHistory.type[parseInt(text)]
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
      cfuhType:customerFollowUpHistory.type,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
      // 跟进
      followForm:this.$form.createForm(this, {name: 'coordinated'}),
      followVisible :false,
      followConfirmLoading :false,
      followType:customerFollowUpHistory.type,
      followTraceResult:customerFollowUpHistory.traceresult,
      dictionaryDetailsFollow:[],
      // 员工表
      employeeList:[]
    }
  },
  async mounted() {
    // id 10 跟进方式
    const {data} = await dictionaryDetails.list({page:1,size:999999,id:10})
    this.dictionaryDetailsFollow = data.data.list

    const employeeData = await employee.list({page:1,size:99999})
    this.employeeList = employeeData.data.data.list

    await this.query()
    this.queryForm.setFieldsValue({"type":9999999})
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
      customerFollowUpHistory.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.list.map((e, i) => ({
          ...e,
          key: i + "",
          tracetypeFind:this.dictionaryDetailsFollow.find(d=>d.id===e.tracetype).title,
          inputuserFind:this.employeeList.find(eL=>eL.id===e.inputuser).name,
          }))
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      })
    },
    updateItem(id) {
      this.showModal('更改')
      customerFollowUpHistory.getDetail(id).then(({data}) => {
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"sn": data.data["sn"]})
        this.form.setFieldsValue({"title": data.data["title"]})
        this.form.setFieldsValue({"intro": data.data["intro"]})
      })
    },
    // 移交
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

        customerFollowUpHistory['update'](values).then(({data}) => {
          this.followConfirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message:'跟进记录出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message:'成功',
              description:'跟进记录成功',
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
        this.followForm.setFieldsValue({id})
        this.followForm.setFieldsValue({name:line.name})
        this.followForm.setFieldsValue({comment:line.comment})
        this.followForm.setFieldsValue({tracedetails:line.tracedetails})
        this.followForm.setFieldsValue({tracetime:new moment(new Date())})
        this.followForm.setFieldsValue({tracetype:this.dictionaryDetailsFollow[0].id})
        this.followForm.setFieldsValue({type:Object.keys(this.followType)[0]})
        this.followForm.setFieldsValue({traceresult:Object.keys(this.followTraceResult)[0]})
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
