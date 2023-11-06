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

      <a-form-model-item label="线索或商机">
        <a-radio-group v-model="form.bizType">
          <a-radio value="1">
            商机（客户已经立项）
          </a-radio>
          <a-radio value="2">
            线索（客户未立项）
          </a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="来源分类">
        <a-radio-group v-model="form.resource">
          <a-radio value="1">
            渠道报备
          </a-radio>
          <a-radio value="2">
            官网、互联网
          </a-radio>
          <a-radio value="3">
            市场活动
          </a-radio>
          <a-radio value="4">
            其他
          </a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="商机产品：">
        <a-select   mode="multiple" v-model="form.products" placeholder="请您选择产品">
          <a-select-option v-for="prd in productsDataSource" :key="prd.id" :value="prd.id">
            {{ prd.productName }}-{{prd.productUnitPrice}} -{{prd.productUnit}}
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="项目商机描述：">
        <a-input v-model="form.desc" type="textarea" placeholder="针对这个项目，你设计了什么样的成功路径"/>
      </a-form-model-item>

      <a-form-model-item label="预计成交时间">
        <a-month-picker  v-model="form.okdate"  placeholder="选择 月份" />
      </a-form-model-item>

      <a-form-model-item label="预算金额">
        <a-input v-model="form.desc" type="number" placeholder="大概金额"/>
      </a-form-model-item>


      <a-divider dashed />

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
      console.log('submit!', this.form);
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