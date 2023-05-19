<template>
  <a-drawer
      title="企业详情"
      placement="right"
      :visible="visible"
      :width="1000"
      class="good-bg"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
  >

    <a-descriptions title="深圳一粒云科技有限公司">
      <a-descriptions-item label="UserName">
        Zhou Maomao
      </a-descriptions-item>
      <a-descriptions-item label="Telephone">
        1810000000
      </a-descriptions-item>
      <a-descriptions-item label="Live">
        Hangzhou, Zhejiang
      </a-descriptions-item>
      <a-descriptions-item label="Remark">
        remark
      </a-descriptions-item>
      <a-descriptions-item label="Address">
        No. 18, Wantang Road, Xihu District, Hangzhou, Zhejiang, China
      </a-descriptions-item>

    </a-descriptions>

    <a-divider></a-divider>

    <div>
      <a-tabs default-active-key="1" @change="checkPre">
        <a-tab-pane key="1" tab="高级属性">
          <ent-advanced-props></ent-advanced-props>
        </a-tab-pane>

        <a-tab-pane key="4" tab="项目信息">
          <ent-projects></ent-projects>
        </a-tab-pane>

        <a-tab-pane key="3" tab="联系人">
          <ent-contacts></ent-contacts>
        </a-tab-pane>

        <a-tab-pane key="5" tab="客户环境">
          <ent-note></ent-note>
        </a-tab-pane>

        <a-tab-pane key="2" tab="跟进日志" force-render>
          <ent-follows></ent-follows>
        </a-tab-pane>

        <a-tab-pane key="6" tab="技术记录">
          Content of Tab Pane 3
        </a-tab-pane>
        <a-tab-pane key="7" tab="商务信息">
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


export default {
  /**
   * @name: enterpriseInfoAll
   * @author: Administrator
   * @date: 2022/10/18 9:49
   * @description：enterpriseForm
   * @update: 2022/10/18 9:49
   */
  name: "enterpriseInfoAll",
  components:{EntAdvancedProps,EntProjects,EntContacts,EntNote,EntFollows},
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