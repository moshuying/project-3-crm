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
              <a-select
                  style="width: 6rem"
                  v-decorator="['status',{ rules: [{ required: true, message: '状态' }] }]">
                <a-select-option
                    :value="key"
                    :key="index"
                    v-for="(value,key,index) in statusMap">
                  {{value}}
                </a-select-option>
              </a-select>
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
            :scroll="{ x: 1500, y: 300 }"
        >
           <span slot="action" slot-scope="text">
             <a-button type="link" shape="round" icon="edit" size="small" @click="updateItem(text.id,text)" >编辑</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showFollowModal(text.id,text)" >跟进</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showHandoverModal(text.id)">移交</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="showStatusModal(text.id)" >修改状态</a-button>
           </span>
        </a-table>
      </div>
    </a-card>



    <!--企业表单-->
    <enterprise-form :getEntList="getEntList" ref="enterpriseForm"></enterprise-form>

  </div>
</template>

<script>
import * as customerManager from "@/services/customerManager";
import EnterpriseForm from "@/pages/customer/enterpriseForm";

const baseColumns =[
  {
    width:120,
    title: '企业名称',
    dataIndex: 'entName',
    ellipsis: true,
    fixed: 'left'
  },
  {
    title: '合作类型',
    dataIndex: 'entCooperationType',
  },
  {
    width:60,
    title: '来源',
    dataIndex: 'source',
    ellipsis: true,
  }
]
const columns = [
  {
    width:60,
    title: '编号',
    dataIndex: 'id',
    fixed: 'left'
  },
  ...baseColumns,
  {
    title: '营销人员',
    dataIndex: 'inputuser'
  },
  {
    title: '状态',
    dataIndex: 'status',
    customRender:(text)=>customerManager.statusMap[parseInt(text)]
  },
  {
    width:380,
    title: '操作',
    scopedSlots: {customRender: 'action'},
    fixed: 'right'
  }
]

export default {
  /**
   * @name: enterprise
   * @author: Administrator
   * @date: 2022/10/10 11:19
   * @description：enterprise
   * @update: 2022/10/10 11:19
   */
  name: "enterprise",
  components: {EnterpriseForm},
  data() {
    return {
      columns: columns,
      queryForm:this.$form.createForm(this, {name: 'enterpriseQuery'}),
    }
  },

  methods:{

    getEntList(){

    },
    handleTableChange(){

    },
    showModal() {
      // this.visible = true;
      this.$refs["enterpriseForm"].showDrawer();
    },
  }
}
</script>

<style scoped>

</style>