<template>
  <a-card title="一粒云产品商机报备（渠道专用）">

    <FormulateForm @submit="handAddprts"
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
      values: {},
      schema: [
        {
          type: 'text',
          name: 'productName',
          label: '商机名称',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'textarea',
          name: 'productName',
          label: '项目商机描述，便于厂家配备资源',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'select',
          name: 'productUnit',
          label: '关联产品',
          // options: this.dictionaryDetailsUnit,
          validation: '^required',
          validationName: 'Password confirmation'
        },
        {
          type: 'text',
          name: 'productName',
          label: '商机归属方（渠道名称或直接客户名称）',
          validation: 'required',
          placeholder: "Sample placeholder"
        },
        {
          type: 'text',
          name: 'productName',
          label: '报备人(联系人)姓名',
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
          label: '报备人邮箱地址',
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
  methods: {

    // 提交
    handAddprts() {

    }
  }
}
</script>

<style scoped>


</style>