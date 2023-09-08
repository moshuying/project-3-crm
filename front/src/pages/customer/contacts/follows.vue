<template>
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
        <a-input v-decorator="['customerEntId',{ rules: [{ required: false}] }]"/>
      </a-form-item>

      <a-form-item disabled label="企业名称">
        <a-input
            v-decorator="['entName', { rules: [{ required: true, message: '企业名称'  }]}]"
            disabled/>
      </a-form-item>
      <a-form-item disabled label="客户姓名">
        <a-input
            v-decorator="['name', { rules: [{ required: true, message: '姓名'  }]}]"
            disabled/>
      </a-form-item>
      <a-form-item disabled label="跟进时间">
        <a-date-picker
            show-time
            v-decorator="['tracetime', { rules: [{ required: true, message: '跟进时间'  }]}]"
        />
      </a-form-item>

      <a-form-item disabled label="跟进方式">
        <a-select
            v-decorator="['tracetype', { rules: [{ required: true, message: '跟进方式'  }]}]">
          <a-select-option
              :value="value.id"
              :key="index"
              v-for="(value,key,index) in dictionaryDetailsFollow">
            {{ value.title }}
          </a-select-option>
        </a-select>
      </a-form-item>

      <a-form-item disabled label="跟进内容">
        <a-input
            v-decorator="['tracedetails', { rules: [{ required: true, message: '跟进内容',min:1,max:120,message:'跟进内容长度在1到120之间'  }]}]"
        />
      </a-form-item>

      <a-form-item disabled label="备注">
        <a-textarea
            v-decorator="['comment', { rules: [{ required: true, message: '备注' ,min:1,max:120,message:'备注内容在1到120之间'}]}]"
            :auto-size="{ minRows: 3, maxRows: 5 }"
        />
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script>
import * as customerFollowUpHistory from "@/services/customerFollowUpHistory";
import * as dictionaryDetails from "@/services/dictionaryDetails";
import moment from "moment";

export default {
  /**
   * @name: follows  跟进客户，跟进项目，跟进联系人；一般跟进联系人，然后就是跟进项目，主要也是跟进联系人；
   * @author: Administrator
   * @date: 2023/9/8 11:17
   * @description：follows
   * @update: 2023/9/8 11:17
   */
  name: "follows",
  data() {
    return {
      // 跟进

      followForm: this.$form.createForm(this, {name: 'coordinated'}),
      followVisible: false,
      followConfirmLoading: false,
      followType: customerFollowUpHistory.type,
      followTraceResult: customerFollowUpHistory.traceresult,
    }
  },

  mounted() {
    // id 10 跟进方式
    dictionaryDetails.list({page: 1, size: 999999, id: 10}).then(({data}) => {
      this.dictionaryDetailsFollow = data.data.records
    })
  },

  methods: {
    // 跟进
    handleFollowCancel() {
      this.followVisible = false;
      this.followConfirmLoading = false
      this.followForm.resetFields()
    },

    handleFollowOk() {
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
          } else {
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
    async showFollowModal(id, line) {

      console.log(id, line)

      this.followVisible = true;
      this.followConfirmLoading = false
      await this.followForm.resetFields()
      // 这里不能循环
      this.$nextTick(() => {
        this.followForm.setFieldsValue({entName: line.entName})
        this.followForm.setFieldsValue({customerEntId: line.customerEntId})
        this.followForm.setFieldsValue({customerid: line.id})
        this.followForm.setFieldsValue({name: line.name})
        this.followForm.setFieldsValue({tracetime: new moment(new Date())})
        this.followForm.setFieldsValue({tracetype: this.dictionaryDetailsFollow[0].id})  //跟进方式
        this.followForm.setFieldsValue({type: Object.keys(this.followType)[0]})
        this.followForm.setFieldsValue({traceresult: Object.keys(this.followTraceResult)[0]})  // 跟进结果
      })
    },
  }
}
</script>

<style scoped>

</style>