<template>
  <a-drawer
      placement="right"
      :visible="visible"
      :width="1000"
      class="good-bg"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
  >
   <template v-slot:title>
      企业名称：{{curEnterprise.entName}}
     <a-button type="primary" @click="updateEnterprise(curEnterprise)" icon="edit">
       编辑/更新
     </a-button>
   </template>

    <a-descriptions title="详细信息">
      <a-descriptions-item label="法人">
        {{ curEnterprise.entBoss }}
      </a-descriptions-item>
      <a-descriptions-item label="电话">
        {{ curEnterprise.entTel }}
      </a-descriptions-item>
      <a-descriptions-item label="邮箱">
        {{ curEnterprise.entEmail }}
      </a-descriptions-item>
      <a-descriptions-item label="公司网站">
        {{ curEnterprise.entWebsite }}
      </a-descriptions-item>
      <a-descriptions-item label="地址">
        {{ curEnterprise.entAddr}}
      </a-descriptions-item>
      <a-descriptions-item label="信用代码">
        {{ curEnterprise.entSn}}
      </a-descriptions-item>
      <a-descriptions-item label="简介">
        {{ curEnterprise.entDesc}}

      </a-descriptions-item>
    </a-descriptions>

    <add-enterprise-form ref="addOrUpdate"></add-enterprise-form>

    <a-divider></a-divider>

    <div>
      <a-tabs default-active-key="1" @change="checkPre">

        <a-tab-pane key="1" tab="跟进日志" force-render>
          <ent-follows :curEnterprise = curEnterprise></ent-follows>
        </a-tab-pane>

        <a-tab-pane key="2" tab="项目信息">
          <ent-projects></ent-projects>
        </a-tab-pane>

        <a-tab-pane key="3" tab="联系人">
          <ent-contacts></ent-contacts>
        </a-tab-pane>

        <a-tab-pane key="4" tab="技术记录">
          Content of Tab Pane 3
        </a-tab-pane>

        <a-tab-pane key="5" tab="客户环境">
          <ent-note></ent-note>
        </a-tab-pane>

        <a-tab-pane key="6" tab="商务信息">
        </a-tab-pane>

        <a-tab-pane key="7" tab="高级属性">
          <ent-advanced-props></ent-advanced-props>
        </a-tab-pane>
      </a-tabs>
    </div>

  </a-drawer>
</template>

<script>

import * as dictionaryDetails from "@/services/dictionaryDetails";
import EntAdvancedProps from "@/pages/customer/enterpriseSub/entAdvancedProps";
import EntProjects from "@/pages/customer/enterpriseSub/entProjects";
import EntContacts from "@/pages/customer/enterpriseSub/entContacts";
import EntNote from "@/pages/customer/enterpriseSub/entNote";
import EntFollows from "@/pages/customer/enterpriseSub/entFollows";
import AddEnterpriseForm from "@/pages/customer/addEnterpriseForm";
import {mapState} from "vuex";


export default {
  /**
   * @name: enterpriseInfoAll
   * @author: Administrator
   * @date: 2022/10/18 9:49
   * @description：enterpriseForm
   * @update: 2022/10/18 9:49
   */
  name: "enterpriseInfoAll",
  components:{AddEnterpriseForm, EntAdvancedProps,EntProjects,EntContacts,EntNote,EntFollows},
  // props: ["curEnterprise"],
  data() {
    return {
      visible: false,
      entId: null,
    //  currtEnterprise: this.$store.state.enterprise.curEnterprise,
      // 构造json表表单， 参考： https://vueformulate.com/guide/forms/generating-forms/
     // entCooType: [],
    }
  },

  methods: {

    //打开传递参数；
    showDrawer(entId,enterprise) {
      this.visible = true;
      this.entId= entId;
      this.$store.commit("enterprise/setCurEnterprise",JSON.parse(JSON.stringify(enterprise)))
      console.log("showDrawer",this.showEnterprise);
    },

    updateEnterprise(currtEnterprise){
      this.$refs["addOrUpdate"].showDrawer("up",currtEnterprise);
    },

    onClose() {
      this.visible = false;
    },

    checkPre(){
      console.log("check pre")
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
    // this.loadEntCooType()
  },
  computed: mapState('enterprise', ['curEnterprise', 'advanceProps']) //

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