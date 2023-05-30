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

    <!--    加载企业在线查询页面-->
    <!--    <query-ent-modal ref="queryEnt"></query-ent-modal>-->

  </a-drawer>
</template>

<script>
// import * as dictionaryDetails from "@/services/dictionaryDetails";
import * as customerEnterprise from "@/services/customerEnterprise"
// import QueryEntModal from "@/pages/customer/queryEntModal";

export default {
  /**
   * @name: addEnterpriseForm
   * @author: Administrator
   * @date: 2022/10/18 9:49
   * @description：enterpriseForm
   * @update: 2022/10/18 9:49
   */
  name: "addEnterpriseForm",
  // components: {QueryEntModal},
  props: {
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
          name: 'entBoss',
          label: '法人姓名',
        },
        {
          type: 'text',
          name: 'entTel',
          label: '电话',
          help: "输入可验证电话"
        },
        {
          type: 'text',
          name: 'entEmail',
          label: '邮箱',
          help: "输入邮箱"
        },
        {
          type: 'text',
          name: 'entsn',
          label: '统一社会信用代码',
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
          name: 'entDesc',
          label: '简介',
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
    focusUsername() {
      console.log("openQuery:")
    },
    openQuery() {
      console.log("asdfasdf")
      if (this.values.entName) {
        window.open("https://aiqicha.baidu.com/s?q=" + encodeURIComponent(this.values.entName) + "&t=0")
      }
    },

    // add
    handAddCusEnt(data) {
      console.log(data)
      return customerEnterprise.add(data).then((res) => {
        console.log("---------------",res)
        console.log("getCusEntList1")
        this.$emit("getCusEntList");
        this.onClose()
      }).then(() => {
        console.log("getCusEntList2")
        this.$emit("getCusEntList");
      })
    },

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