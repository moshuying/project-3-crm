<template>
  <div>
    <a-button type="link">
      新增跟进
    </a-button>
    {{customerFollowUpHistorys}}
    <a-divider />
    <a-steps progress-dot :current="1" direction="vertical">
      <a-step v-for="followHis in customerFollowUpHistorys"  :key="followHis.id"  >
        <div style="width: 500px" slot="title" > <a-icon type="calendar" theme="twoTone" /> : {{ followHis.tracetime}} , {{followHis.customerName}}</div>
        <div style="width: 800px" slot="description" >跟进内容：  {{ followHis.tracedetails}} </div>
      </a-step>
    </a-steps>

    <a-pagination v-model="pagination.current" :total="pagination.total"  @change="onPageChange" show-less-items />

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
    },

    // 分页
    onPageChange(page, pageSize){
      console.log("onPageChange:::",page, pageSize,this.pagination)
      this.pagination.page = page
      this.fetchFllowHistory(this.pagination)
    }

  },



  mounted() {
    this.fetchFllowHistory();
  }

}
</script>

<style >
.ant-steps-item-content{
  width: 800px !important;
}
</style>