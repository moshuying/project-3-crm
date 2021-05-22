<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false}] }]"
                  placeholder="请输入姓名/邮箱"
              />
            </a-form-item>
            <a-form-item label="跟进时间">
              <a-range-picker
                  :show-time="{ format: 'HH:mm' }"
                  v-decorator="['rangeTime', { rules: [{ required: false}] }]"
                  format="YYYY/MM/DD HH:mm:ss"
                  :placeholder="['开始时间', '结束时间']"
                  @change="onChange"
                  @ok="onOk"
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
        <a-form-item label="数据字典名称">
          <a-input
              v-decorator="['title', { rules: [{ required: true, message: '请输入数据字典名称' }] }]"
          />
        </a-form-item>
        <a-form-item label="数据字典编号">
          <a-input
              v-decorator="['sn',{ rules: [{ required: true, message: '请输入数据字典编号' }] },]"
              placeholder="请输入数据字典编号"
          />
        </a-form-item>
        <a-form-item label="数据字典简介">
          <a-input
              v-decorator="['intro',{ rules: [{ required: true, message: '请输入数据字典简介' }] },]"
              placeholder="请输入数据字典简介"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import * as customerFollowUpHistory from "@/services/customerFollowUpHistory"
import * as employee from "@/services/employee"
import * as dictionaryDetails from "@/services/dictionaryDetails";

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
      // modal form
      form: this.$form.createForm(this, {name: 'coordinated'}),
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
        console.log(values)
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
    // modal
    showModal(title = '添加') {
      this.visible = true;
      this.title = title || '添加'
      this.form.resetFields()
    },
    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {

        if (err) {
          console.log("form error");
          return;
        }
        customerFollowUpHistory['update'](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '数据字典信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '数据字典信息成功',
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
    },
    onChange(value, dateString) {
      console.log('Selected Time: ', value);
      console.log('Formatted Selected Time: ', dateString);
    },
    onOk(value) {
      console.log('onOk: ', value);
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
