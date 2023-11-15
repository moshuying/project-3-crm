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
    <a-form-model :model="bizform" :label-col="labelCol" :wrapper-col="wrapperCol">

      <a-form-model-item label="线索或商机">
        <a-radio-group v-model="bizform.bizType">
          <a-radio value=1>
            商机（客户已经立项）
          </a-radio>
          <a-radio value=2>
            线索（客户未立项）
          </a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="来源分类">
        <a-radio-group v-model="bizform.bizResources">
          <a-radio value=1>
            渠道报备
          </a-radio>
          <a-radio value=2>
            官网、互联网
          </a-radio>
          <a-radio value=3>
            市场活动
          </a-radio>
          <a-radio value=4>
            其他
          </a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="客户名称：">
        <a-input v-model="bizform.bizEntName" type="input" placeholder="客户名称"/>
      </a-form-model-item>

      <a-form-model-item label="商机产品：">
        <a-select   mode="multiple" v-model="bizform.products" @change="prdhandleChange" placeholder="请您选择产品">
          <a-select-option v-for="prd in productsDataSource" :key="prd.id" :value="prd">
            {{ prd.productName }}-{{prd.productUnitPrice}} -{{prd.productUnit}}
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="项目商机描述：">
        <a-input v-model="bizform.bizDesc" type="textarea" placeholder="针对这个项目，你设计了什么样的成功路径"/>
      </a-form-model-item>

      <a-form-model-item label="预计成交时间">
        <a-month-picker  v-model="bizform.bizOkday"  placeholder="选择 月份" />
      </a-form-model-item>

      <a-form-model-item label="预算金额">
        <a-input v-model="bizform.bizCount" type="number" placeholder="大概金额"/>
      </a-form-model-item>


      <a-divider dashed />

    </a-form-model>

  </a-modal>
</template>

<script>
import * as products  from "@/services/products";
import * as bizs  from "@/services/bizs";
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
      bizform: {
        bizType:1,
        bizEntName:null,
        bizResources:2,
        products:[],
        bizDesc:"",
        bizOkday:null,
        bizCount:500
      }

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
      console.log('submit!', this.bizform);
      bizs.add(this.bizform).then((res)=>{
        console.log("res:",res)
      })
    },

    /**
     * 选择产品设置对象
     */
    prdhandleChange(value){
      console.log("lll",value);
    },

    handleCancel() {
      this.visible = false;
    },
    onSubmit() {
      console.log('submit!', this.bizform);
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
      this.bizform.setFieldsValue({entId: value})
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