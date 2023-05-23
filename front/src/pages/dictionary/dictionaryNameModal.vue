<template>
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
            v-decorator="['title', { rules: [{ required: true,min:1,max:15,message:'内容长度在1到15之间' }] }]"
        />
      </a-form-item>
      <a-form-item label="数据字典编号">
        <a-input
            v-decorator="['sn',{ rules: [{ required: true, min:1,max:30,message:'输入内容应在1到50位之间' }] },]"
            placeholder="请输入数据字典编号"
        />
      </a-form-item>
      <a-form-item label="数据字典简介">
        <a-input
            v-decorator="['intro',{ rules: [{ required: true, min:3,max:100,message:'内容长度在3到100之间' }] },]"
            placeholder="请输入数据字典简介"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import * as dictionaryContents from "@/services/dictionaryContents";

export default {
  /**
   * @name: dictionaryName
   * @author: Administrator(jiuyuehe)
   * @date: 2023/5/22 15:24
   * @description：dictionaryName
   * @update: 2023/5/22 15:24
   */
  name: "dictionaryNameModal",
  data(){

    return{
      // modal
      title: '添加',
      visible: false,
      onfirmLoading: false,
      // modal form
      form: this.$form.createForm(this, {name: 'coordinated'}),
    }
  },
  methods:{
    showModal(title = '添加') {
      this.visible = true;
      this.title = title || '添加'
     // this.form.resetFields()
    },
    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {
        if (err) {
          this.confirmLoading = true;
          console.log("form error");
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';

        dictionaryContents[method](values).then(({data}) => {
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
          this.query()
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

<style scoped>

</style>