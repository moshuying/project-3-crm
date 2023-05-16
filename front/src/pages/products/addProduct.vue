<template>


  <a-drawer
      title="新增 product"
      placement="right"
      :closable="false"
      :visible="visible"
      :width="500"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
  >
    <FormulateForm  @submit="handAddprts"
        v-model="values"
        :schema="schema"
    />

    {{values}}
  </a-drawer>


</template>

<script>
import * as dictionaryDetails from "@/services/dictionaryDetails";
import * as products from "@/services/products";



export default {
  /**
   * @name: addProduct
   * @author: Administrator
   * @date: 2022/10/13 13:45
   * @description：addProduct
   * @update: 2022/10/13 13:45
   */
  name: "addProduct",

  props: {
    getProduct:null,
  },

  data() {
    return {
      values:{},
      visible: false,
      dictionaryDetailsUnit: [],

      // 构造json表表单， 参考： https://vueformulate.com/guide/forms/generating-forms/
      schema: [
        {
          type: 'text',
          name: 'productName',
          label: '产品名称',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'select',
          name: 'productUnit',
          label: '单位',
         // options: this.dictionaryDetailsUnit,
          validation: '^required',
          validationName: 'Password confirmation'
        },
        {
          type: 'number',
          name: 'productUnitPrice',
          label: '单价（元）',
          validation : "required|number|between:1,10000000"
        },
        {
          type: 'textarea',
          name: 'productDesc',
          label: '产品描述',
          validation : "required",
          help:"报价描述详情，从这里取出"
        },
        {
          type: 'submit',
          label: '确认添加',
          name: 'submit',
        }
      ],

      productFormMode: "add",
    }
  },
  methods: {
    showDrawer() {
      this.visible = true;
      this.values =  {}
    },
    onClose() {
      this.visible = false;
    },


    updateProduct(id, item){
      this.productFormMode = "update";
      console.log("updateProduct",id, item)
      this.visible = true
      this.values = item;
      // let result = products.add(this.values)
      // console.log(result)
    },

    delProduct(id, item){
      this.productFormMode = "del";
      console.log("del", id, item)
    },

    // form 表单提交操作。
    handAddprts (data) {
      console.log("submitHandler data",data)
      console.log(" console.log(this.getProducts)",this.getProducts)
      products.addOrUpdata(data).then((data)=>{
        //console.log("prts:", data)
        if(data.status === 200){
          this.visible = false;
          this.getProduct();
          // 刷新列表
         // console.log("this.getProducts", this.getProducts)
        }else{
          console.log(data)
        }
      })
    },

    saveProduct(){
    },

    //mount 时回调，将生成的form表单填充
    loadProductUnit() {

      //取字典（單位：id：15）
     return  dictionaryDetails.list({page: 1, size: 999999, id: 15}).then(({data}) => {
       // this.dictionaryDetailsUnit = data.data.records
        if(data.data.records){
          this.dictionaryDetailsUnit = data.data.records.map(item => {
            return {
              key: item.id,
              value: item.title,
              label: item.title
            }
          })

          this.schema.forEach((item)=>{
            if(item.name === "productUnit"){
              item.options = this.dictionaryDetailsUnit
            }
          })
        }
      })
    },

  },
  mounted() {
    this.loadProductUnit()
  },


}
</script>

<style scoped>

</style>