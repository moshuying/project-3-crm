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
             <a-button type="link" shape="round" icon="edit" size="small" @click="bizInfo(text.id,text)" >详情</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="bizDel(text.id,text)" >删除</a-button>
           </span>
        </a-table>
      </div>
    </a-card>

    <add-or-up-bizs ref="bizForm"></add-or-up-bizs>

  </div>
</template>

<script>
import AddOrUpBizs from "@/pages/bizs/addOrUpBizs";

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '产品',
    dataIndex: 'name',
  },
  {
    title: '描述',
    dataIndex: 'tracetime',
  },
  {
    title: '跟进内容',
    dataIndex: 'tracedetails',
  },
  {
    title: '跟进方式',
    dataIndex: 'tracetypeFind',
  },
  {
    title: '录入人',
    dataIndex: 'inputuserFind',
  },
  {
    title: '跟进类型',
    dataIndex: 'type',
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]
export default {
  /**
   * @name: bizs 商机管理
   * @author: Administrator
   * @date: 2023/9/14 17:14
   * @description：niche
   * @update: 2023/9/14 17:14
   */
  name: "bizs",
  components: {AddOrUpBizs},
  data(){
    return{
      columns:columns
    }
  },
  methods:{

    query(){},

    handleTableChange(){},

    // 展示
    showModal(){
      this.$refs["bizForm"].showModal();
    },

    bizInfo(){

    },
    bizDel(){

    }
  }
}
</script>

<style scoped>

</style>