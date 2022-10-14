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
    <FormulateForm
        v-model="values"
        :schema="schema"
    />

    {{values}}
  </a-drawer>


</template>

<script>
import * as dictionaryDetails from "@/services/dictionaryDetails";
// import * as products from "@/services/products";


export default {
  /**
   * @name: addProduct
   * @author: Administrator
   * @date: 2022/10/13 13:45
   * @description：addProduct
   * @update: 2022/10/13 13:45
   */
  name: "addProduct",
  data() {
    return {
      values:{},
      visible: false,
      dictionaryDetailsUnit: [],
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
          label: '单价',
          validation : "required|number|between:1,10000000"
        },
        {
          type: 'submit',
          label: '确认添加'
        }
      ]



    }
  },
  methods: {

    showDrawer() {
      this.visible = true;
    },
    onClose() {
      this.visible = false;
    },
    handleOk() {

      //this.visible = false;
      // eslint-disable-next-line no-unused-vars
      let a = this.fApi.formData()
      a = null;
      // products.add(prts).then((data)=>{
      //   console.log("prts:", data)
      // })


    },

    saveProduct(){

      // product.add(prts).then((data)=>{
      //   console.log("prts:", data)
      // })
    },

    updateUnit() {
      if (!this.fApi.getRule('productUnit').options.length) {
        console.log(this.dictionaryDetailsUnit, this.getOption())
        this.fApi.getRule('productUnit').options.push(...this.getOption())
      }
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

         console.log("this.schema：", this.schema)
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