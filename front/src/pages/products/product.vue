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
        >
           <span slot="action" slot-scope="text">
             <a-button type="link" shape="round" icon="edit" size="small" @click="updateItem(text.id,text)" >编辑</a-button>
             <a-button type="link" shape="round" icon="edit" size="small" @click="delItem(text.id,text)" >删除</a-button>
            <a-button type="link" shape="round" icon="edit" size="small" @click="lockItem(text.id,text)" >锁定</a-button>
           </span>
        </a-table>
      </div>
    </a-card>

    <!--添加产品-->
    <add-product :refer="getProductsList" ref="addProduct"></add-product>

  </div>
</template>


<script>
import * as products from "@/services/products";
import AddProduct from "@/pages/products/addProduct";

const baseColumns =[
  {
    width:180,
    title: '产品名称',
    dataIndex: 'productName',
    ellipsis: true,
  },
  {
    width:120,
    title: '产品单位',
    dataIndex: 'productUnit',
  },
  {
    width:120,
    title: '产品单价',
    dataIndex: 'productUnitPrice',
    ellipsis: true,
  }
]
const columns = [
  {
    width:60,
    title: '编号',
    dataIndex: 'id',
  },
  ...baseColumns,
  {
    title: '添加时间',
    dataIndex: 'createTime'
  },
  {
    width:60,
    title: '状态',
    dataIndex: 'status',
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'},
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
  components: {AddProduct},
  data() {
    return {
      columns: columns,
      products: null,
      dataSource: null,
      pagination: {current:1},
      loading: false,
      visible: false
    }
  },
  mounted() {

    //产品列表
    this.getProductsList()
  },

  methods:{

    //列表加载
    getProductsList(){
      return products.list({page:1,size:999999}).then(({data})=>{
        this.dataSource = data.data.records
      });
    },

    handleTableChange(){

    },
    queryLoading(){

    },

    updateItem(id, item){
      console.log("updateItem",id, item)
    },


    showModal() {
     // this.visible = true;
      this.$refs["addProduct"].showDrawer();
    },
    handleOk(e) {
      console.log(e);
      this.visible = false;
    },

  }
}
</script>

<style scoped>

</style>