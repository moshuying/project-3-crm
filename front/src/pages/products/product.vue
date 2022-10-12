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
             <a-button type="link" shape="round" icon="edit" size="small" @click="delItem(text.id,text)" >删除</a-button>
            <a-button type="link" shape="round" icon="edit" size="small" @click="lockItem(text.id,text)" >锁定</a-button>
           </span>
        </a-table>
      </div>
    </a-card>
  </div>
</template>

<script>
import * as customerManager from "@/services/customerManager";
import * as products from "@/services/products";

const baseColumns =[
  {
    width:120,
    title: '产品名称',
    dataIndex: 'productName',
    ellipsis: true,
    fixed: 'left'
  },
  {
    title: '产品单位',
    dataIndex: 'productUnit',
  },
  {
    width:60,
    title: '产品单价',
    dataIndex: 'productPrice',
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
    title: '添加时间',
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
   * @name: product
   * @author: Administrator
   * @date: 2022/10/10 11:19
   * @description：product
   * @update: 2022/10/10 11:19
   */
  name: "product",
  data() {
    return {
      columns: columns,
      products: null,
      dataSource: null,
      pagination: {current:1},
      loading: false,
    }
  },
  mounted() {

    //产品列表
    products.list({page:1,size:999999}).then(({data})=>{
      this.dataSource = data.data.records
    });
  },

  methods:{

    handleTableChange(){

    },
    queryLoading(){

    },

  }
}
</script>

<style scoped>

</style>