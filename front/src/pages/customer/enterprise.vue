<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false,min:1,max:120,message:'输入长度应在1到120之间'}] }]"
                  placeholder="请输入姓名/电话"
              />
            </a-form-item>
            <a-form-item label="状态">
<!--              <a-select-->
<!--                  style="width: 6rem"-->
<!--                  v-decorator="['status',{ rules: [{ required: true, message: '状态' }] }]">-->
<!--                <a-select-option-->
<!--                    :value="key"-->
<!--                    :key="index"-->
<!--                    v-for="(value,key,index) in statusMap">-->
<!--                  {{value}}-->
<!--                </a-select-option>-->
<!--              </a-select>-->
            </a-form-item>
            <a-form-item>
              <a-button :loading="queryLoading" @click="query()">查询</a-button>
            </a-form-item>
          </a-form>
          <a-button type="primary" @click="showModal('新增')">添加</a-button>
        </a-space>

        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"

        >
          <a slot="name" slot-scope="text">{{ text }}</a>

           <span slot="action" slot-scope="text">
             <a-button type="link" shape="round" icon="edit" size="small" @click="showEnterpriseDetails(text.id,text)" >详情</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showFollowModal(text.id,text)" >删除</a-button>
<!--             <a-button type="link" shape="round" icon="edit" size="small" @click="showHandoverModal(text.id)">移交</a-button>-->
<!--             <a-button type="link" shape="round" icon="edit" size="small" @click="showStatusModal(text.id)" >修改状态</a-button>-->
           </span>
        </a-table>
      </div>
    </a-card>

<!--新增企业表单-->
    <add-enterprise-form @getCusEntList="getCusEntList"  ref="enterpriseForm"></add-enterprise-form>

<!--    企业客户详情表-->
    <enterprise-info-all  :curEnterprise="curEnterprise"  ref="enterpriseInfo"></enterprise-info-all>

  </div>
</template>

<script>
import * as customerEnterprise from "@/services/customerEnterprise";
import AddEnterpriseForm from "@/pages/customer/addEnterpriseForm";
import EnterpriseInfoAll from "@/pages/customer/enterpriseInfoAll"


const columns = [
  {
    width:60,
    title: '编号',
    dataIndex: 'entId',
  },
  {

    title: '企业名称',
    dataIndex: 'entName',
    ellipsis: true,
  },
  {
    title: '合作类型',
    width: 100,
    dataIndex: 'entCooperationType',
  },

  {
    width: 120,
    title: '营销人员',
    dataIndex: 'entBelongBizerName',
    slots: { title: 'customTitle' },
    scopedSlots: { customRender: 'name' },
  },
  {
    width:60,
    title: '来源',
    dataIndex: 'source',
    ellipsis: true,
  },
  {

    title: '操作',
    scopedSlots: {customRender: 'action'},
  }
]

export default {
  /**
   * @name: enterprise-list
   * @author: Administrator
   * @date: 2022/10/10 11:19
   * @description：enterprise
   * @update: 2022/10/10 11:19
   */
  name: "enterprise",
  components: {AddEnterpriseForm,  EnterpriseInfoAll},

  data() {
    return {
      columns: columns,
      queryForm:this.$form.createForm(this, {name: 'enterpriseQuery'}),
      dataSource: null,
      pagination: {},
      curEnterprise:null
    }
  },

  methods:{

    //列表加载
    getCusEntList(params = {"page": 1, "size": 10}) {
      console.log("params: ",params)
      return customerEnterprise.list(params).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page

        this.dataSource = res.records.map((e, i) => ({
          ...e,
          key: i + "",
        }))

        console.log("this.dataSource :",this.dataSource )

        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      });
    },

    // table
    handleTableChange(pagination) {
      const pager = {...this.pagination};
      pager.current = pagination.current;
      this.pagination = pager;
      this.getCusEntList({
        size: pagination.pageSize,
        page: pagination.current,
      });
    },
    showModal() {
      // this.visible = true;
      this.$refs["enterpriseForm"].showDrawer();
    },

    // 企业详情面板
    showEnterpriseDetails(entId,enterprise){
      this.curEnterprise = enterprise;
      this.$refs["enterpriseInfo"].showDrawer(entId,enterprise);
     // console.log("showEnterpriseDetails",enterprise)
    }
  },
  mounted() {
    this.getCusEntList()
  },

}
</script>

<style scoped>

</style>