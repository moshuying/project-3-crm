<template>
  <a-card  title="一粒云产品商机报备（渠道专用）">

    <FormulateForm  @submit="handAddprts"
                    v-model="values"
                    :schema="schema"
    />
  </a-card>
</template>

<script>
export default {
  /**
   * @name: baobei
   * @author: jiuyuehe
   * @date: 2022/10/10 17:11
   * @description：baobei
   * @update: 2022/10/10 17:11
   */
  name: "baobei",
  data() {
    return {
      values:{},
      schema:[
        {
          type: 'text',
              name: 'productName',
            label: '报备客户（企业）名称',
            validation: 'required',
            placeholder: "Sample placeholder"
        },
        {
          type: 'select',
          name: 'productUnit',
          label: '报备产品',
            // options: this.dictionaryDetailsUnit,
          validation: '^required',
          validationName: 'Password confirmation'
        },
        {
          type: 'textarea',
          name: 'productName',
          label: '项目描述，厂家根据描述配备对应资源',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'select',
          name: 'productUnit',
          label: '项目当前阶段',
          // options: this.dictionaryDetailsUnit,
          validation: '^required',
          validationName: 'Password confirmation'
        },
        {
          type: 'text',
          name: 'productName',
          label: '报备人姓名',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'text',
          name: 'productName',
          label: '报备人手机号码',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'text',
          name: 'productName',
          label: '微信openId',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'submit',
          label: '确认添加',
          name: 'submit',
          // "@submit" : this.submitHandler2
        }
      ],
    }
  },
  methods:{

    // 提交
    handAddprts(){

    }
  }
}
</script>

<style scoped>


</style>