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

      <a-divider dashed />

      <a-form-model-item label="报备渠道：">
        <a-auto-complete  v-model="form.entName"
                          style="width: 200px"
                          placeholder="搜索渠道企业名称"
                          @search="onSearch"
                          @change="onSelect" >
          <template slot="dataSource">
            <a-select-option v-for="ent in entDataSource" :key="ent.entId.toString()">
              {{ent.entId}} - {{ ent.entName }}
            </a-select-option>
          </template>
        </a-auto-complete>

      </a-form-model-item>

      <a-form-model-item label="渠道销售负责人">
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

      <a href="" style="float: right">新客户录入</a>

    </a-form-model>

  </a-modal>
</template>

<script>
import * as products  from "@/services/products";
import * as customerEnterprise from "@/services/customerEnterprise";



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
      productsDataSource:[],
      entDataSource:[],
      form: this.$form.createForm(this, {name: 'bizAU'}),

    }
  },

  mounted(){
    this.getProductsList()  // 加载产品列表；
  },

  methods: {

    showModal(){
      this.visible =true;
    },

    handleOk() {

    },

    handleCancel() {
      this.visible = false;
    },
    onSubmit() {
      console.log('submit!', this.form);
    },

    onSearch(searchText) {
      console.log('searchText', searchText);
      customerEnterprise.list({"keyword":searchText}).then(
          ({data}) => {
            if(!data.data) return;
            this.entDataSource = data.data.records
          }
      )
    },

    onSelect(value) {
      console.log('onChange', value,this.form );
      this.form.setFieldsValue({entId: value})
    },

    async getProductsList(params) {
      let data = await products.list(params)
      console.log("this.data:",data)
      this.productsDataSource = data.data.data.records
      console.log("this.productsDataSource:",this.productsDataSource)
      return  this.productsDataSource
    }
  },



}
</script>

<style scoped>

</style>