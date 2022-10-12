<template>
  <a-card title="一粒云产品商机报备（渠道专用）">
    <a-form-model layout="vertical" :model="form" >

      <a-form-model-item label="报备客户（企业）名称" required>
        <a-input v-model="form.fieldA" placeholder="input placeholder"/>
      </a-form-model-item>

      <a-form-model-item label="报备产品" help="helo you">
        <a-select v-model="form.region" placeholder="please select your zone">
          <a-select-option value="shanghai">
            Zone one
          </a-select-option>
          <a-select-option value="beijing">
            Zone two
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="项目描述，厂家根据描述配备对应资源">
        <a-input v-model="form.fieldB" placeholder="input placeholder"/>
      </a-form-model-item>

      <a-form-model-item label="项目当前阶段">
        <a-select v-model="form.region" placeholder="please select your zone">
          <a-select-option value="1">
            初步接触洽谈
          </a-select-option>
          <a-select-option value="2">
            客户已经测试体验
          </a-select-option>

          <a-select-option value="3">
            选型对比阶段
          </a-select-option>

          <a-select-option value="4">
            功能POC阶段（可能需要定制开发支持）
          </a-select-option>

          <a-select-option value="5">
            计划远期项目（6个月以后）
          </a-select-option>

        </a-select>
      </a-form-model-item>


      <a-form-model-item label="报备人姓名" required>
        <a-input v-model="form.fieldB" placeholder="input placeholder"/>
      </a-form-model-item>

      <a-form-model-item label="报备人手机号码" required>
        <a-input v-model="form.fieldB" placeholder="input placeholder"/>
      </a-form-model-item>


      <a-form-model-item label="对口业务（渠道）经理">
        <a-input v-model="form.fieldB" placeholder="input placeholder"/>
      </a-form-model-item>


      <a-form-model-item label="微信open信息" help="关注公众号自动提示进度">
        <a-input v-model="form.fieldB" placeholder="input placeholder"/>
      </a-form-model-item>

      <br><br>

      <a-form-model-item >
        <a-button type="primary" block>
          提 交
        </a-button>
      </a-form-model-item>
    </a-form-model>
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
      form: {
        fieldA: '',
        fieldB: '',
      },
    }
  }
}
</script>

<style scoped>


</style>