<template>
  <a-drawer
      title="新增 企业"
      placement="right"
      :closable="false"
      :visible="visible"
      :width="500"
      class="good-bg"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
  >
    <FormulateForm @submit="handAddprts"
                   v-model="values"
                   :schema="schema"
                   @open-query="openQuery"
    />

    <span class="black-img"> {{ values }}</span>

    <p></p>
  </a-drawer>
</template>

<script>
import * as dictionaryDetails from "@/services/dictionaryDetails";

export default {
  /**
   * @name: enterpriseForm
   * @author: Administrator
   * @date: 2022/10/18 9:49
   * @description：enterpriseForm
   * @update: 2022/10/18 9:49
   */
  name: "enterpriseForm",

  data() {
    return {
      visible: false,
      values: {},
      // 构造json表表单， 参考： https://vueformulate.com/guide/forms/generating-forms/
      schema: [
        {
          component: 'div',
          class: 'double-row',
          children: [
            {
              name: 'entName',
              type: 'text',
              label: '企业名称',
              validation: 'required',
              placeholder: "请填写企业名称",
              class: 'ch-col'
            },
            {
              type: 'button',
              name: 'open',
              label: '网络验证',
              class: 'ch-col',
              '@click': 'open-query'
            },
          ]
        },
        {
          type: 'select',
          name: 'entCooperationType',
          label: '类型',
          // options: this.dictionaryDetailsUnit,
          validation: '^required',
          validationName: '企业必须分类'
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
          type: 'textarea',
          name: 'productDesc',
          label: '产品描述',
          validation: "required",
          help: "报价描述详情，从这里取出"
        },
        {
          type: 'select',
          name: 'entBelongBizer',
          label: '业务负责人',
          // options: this.dictionaryDetailsUnit,
          validation: '^required',
          validationName: '企业必须分类'
        },

        {
          type: 'submit',
          label: '确认添加',
          name: 'submit',
          // "@submit" : this.submitHandler2
        }
      ],
      entCooType: [],
    }
  },

  methods: {

    // ...mapState('account', {currUser: 'user'}),

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
      if(this.values.entName){
        window.open("https://aiqicha.baidu.com/s?q="+encodeURIComponent(this.values.entName)+"&t=0")
      }
    },

    handAddprts() {

    },

    //mount 时回调，将生成的form表单填充
    loadEntCooType() {
      //取字典（單位：id：15）
      return dictionaryDetails.list({page: 1, size: 999999, id: 16}).then(({data}) => {
        // this.dictionaryDetailsUnit = data.data.records
        if (data.data.records) {
          this.entCooType = data.data.records.map(item => {
            return {
              key: item.id,
              value: item.title,
              label: item.title
            }
          })

          this.schema.forEach((item) => {
            if (item.name === "entCooperationType") {
              item.options = this.entCooType
            }
          })
        }
      })
    },

  },
  mounted() {
    this.loadEntCooType()
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