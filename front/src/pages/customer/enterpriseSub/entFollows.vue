<template>
  <div>
    <a-button type="link">
      新增跟进
    </a-button>
    {{customerFollowUpHistorys}}
    <a-divider />
    <a-steps progress-dot :current="1" direction="vertical">
      <a-step v-for="followHis in customerFollowUpHistorys"  :key="followHis.id"   :description="followHis.tracedetails" >
        <div slot="title" > {{ followHis.tracetime}} , {{followHis.customerName}}</div>
      </a-step>
    </a-steps>
  </div>
</template>

<script>
import * as customerFollowUpHistory from "@/services/customerFollowUpHistory";

export default {

  name: "entFollows",
  data() {
    return {
      loading: false,
      pagination: {"page": 1, "size": 10},
      curEnterprise:null,
      customerFollowUpHistorys:[]
    }
  },

  methods: {
    // 加载跟进历史
    fetchFllowHistory(params = {"page": 1, "size": 10}) {

      console.log("customerFollowUpHistory-------")

      customerFollowUpHistory.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.customerFollowUpHistorys = res.records.map((e, i) => ({
          ...e,
          key: i + "",
          //tracetypeFind: this.dictionaryDetailsFollow.find(d => d.id === e.tracetype).title,
          //inputuserFind: this.employeeList.find(eL => eL.id === e.inputuser).name,
        }))
        console.log("customerFollowUpHistorys::",this.customerFollowUpHistorys)
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      })
    }
  },

  mounted() {
    this.fetchFllowHistory();
  }

}
</script>

<style scoped>
.ant-steps-dot .ant-steps-item-content{
  width: auto !important;
}
</style>