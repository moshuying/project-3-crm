<template>
  <a-modal
      :title="title"
      :visible="visible"
      width="800px"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
      okText="提交"
  >
    <a-form-model :model="form" :label-col="labelCol" :wrapper-col="wrapperCol">

      <a-form-model-item label="项目商机描述：">
        <a-input v-model="form.desc" type="textarea"/>
      </a-form-model-item>

      <a-form-model-item label="项目商机产品：">
        <a-select   mode="multiple" v-model="form.region" placeholder="please select your zone">
          <a-select-option value="shanghai">
            产品1
          </a-select-option>
          <a-select-option value="beijing">
            产品2
          </a-select-option>
          <a-select-option value="bei22jing">
            产品3
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="商机归属企业">
        <a-input v-model="form.name"/>
        <a href="">新客户录入</a>
      </a-form-model-item>

      <a-form-model-item label="商机联系人">
        <a-input v-model="form.name"/>
      </a-form-model-item>


      <a-form-model-item label="联系人">
        <a-checkbox-group v-model="form.type">
          <a-checkbox value="1" name="type">
            Online
          </a-checkbox>
          <a-checkbox value="2" name="type">
            Promotion
          </a-checkbox>
          <a-checkbox value="3" name="type">
            Offline
          </a-checkbox>
        </a-checkbox-group>
      </a-form-model-item>
      <a-form-model-item label="线索或商机">
        <a-radio-group v-model="form.resource">
          <a-radio value="1">
            商机（客户已经立项）
          </a-radio>
          <a-radio value="2">
            线索（客户未立项）
          </a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="预计成交时间">

        <a-month-picker  v-model="form.okdate" :disabled-date="disabledDate" placeholder="Select month" />
      </a-form-model-item>

      <a-form-model-item label="备注">
        <a-input v-model="form.desc" type="textarea"/>
      </a-form-model-item>


      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">
          Create
        </a-button>
        <a-button style="margin-left: 10px;">
          Cancel
        </a-button>
      </a-form-model-item>
    </a-form-model>

  </a-modal>
</template>

<script>
export default {
  /**
   * @name: addOrUpBizs 商机的增删操作
   * @author: Administrator
   * @date: 2023/9/14 17:21
   * @description：addOrUpBizs
   * @update: 2023/9/14 17:21
   */
  name: "addOrUpBizs",

  data() {

    return {
      visible: false,
      confirmLoading: false,
      title: null,
      labelCol: {span: 4},
      wrapperCol: {span: 14},
      form: {
        name: '',
        region: undefined,
        date1: undefined,
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      }
    }
  },

  methods: {

    showModal(){
      this.visible =true;
    },

    handleOk() {

    },

    handleCancel() {

    },
    onSubmit() {
      console.log('submit!', this.form);
    },
  }
}
</script>

<style scoped>

</style>