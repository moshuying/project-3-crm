<template>
  <a-drawer
      title="新增 企业"
      placement="right"
      :visible="visible"
      :width="500"
      class="good-bg"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
  >
    <FormulateForm @submit="handAddCusEnt"
                   v-model="values"
                   :schema="schema"
                   @click="openQuery"
    />

    <span class="black-img"> {{ values }}</span>

    <p></p>
  </a-drawer>
</template>

<script>
// import * as dictionaryDetails from "@/services/dictionaryDetails";
import * as customerEnterprise  from  "@/services/customerEnterprise"

export default {
  /**
   * @name: addEnterpriseForm
   * @author: Administrator
   * @date: 2022/10/18 9:49
   * @description：enterpriseForm
   * @update: 2022/10/18 9:49
   */
  name: "addEnterpriseForm",

  props:{
    freshData: {
      type: Function,
      default: null
    }
  },

  data() {
    return {
      visible: false,
      values: {},
      // 构造json表表单， 参考： https://vueformulate.com/guide/forms/generating-forms/
      schema: [
        {
          name: 'entName',
          type: 'text',
          label: '企业名称',
          validation: 'required',
          placeholder: "请填写企业名称",
        },
        {
          type: 'button',
          label: '爱企查验证',
          "@click": true,
        },
        {
          type: 'text',
          name: 'entAddr',
          label: '企业地址',
        },
        {
          type: 'text',
          name: 'entWebsite',
          label: '企业网站',
          validation: "required",
          help: "url地址"
        },

        {
          type: 'submit',
          label: '确认添加',
          name: 'submit',
        }
      ],
     // entCooType: [],
    }
  },

  methods: {

    showDrawer() {
      this.visible = true;
      this.values = {}
    },
    onClose() {
      this.visible = false;
    },

    focusUsername(){
      console.log("openQuery:")
    },

    openQuery() {
      console.log("asdfasdf")
      if(this.values.entName){
        window.open("https://aiqicha.baidu.com/s?q="+encodeURIComponent(this.values.entName)+"&t=0")
      }
    },

    // add
    handAddCusEnt(data) {
      console.log(data)
      customerEnterprise.add(data).then((res) =>{
        console.log(res)
        this.onClose()
      }).then(()=>{
        console.log("getCusEntList")
       // this.$emit("getCusEntList");
        if(this.freshData){
          this.freshData();
        }
      })


    },

    //mount 时回调，将生成的form表单填充
    // loadEntCooType() {
    //   //取字典（單位：id：15）
    //   return dictionaryDetails.list({page: 1, size: 999999, id: 16}).then(({data}) => {
    //     // this.dictionaryDetailsUnit = data.data.records
    //     if (data.data.records) {
    //       this.entCooType = data.data.records.map(item => {
    //         return {
    //           key: item.id,
    //           value: item.title,
    //           label: item.title
    //         }
    //       })
    //
    //       this.schema.forEach((item) => {
    //         if (item.name === "entCooperationType") {
    //           item.options = this.entCooType
    //         }
    //       })
    //     }
    //   })
    // },

  },
  mounted() {
    // this.loadEntCooType()
  },

}
</script>

<style lang="less">


.ch-col {
  align-self: center;
  margin-right: 10px;
}


.double-row {
  display: flex;
}


</style>