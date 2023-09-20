<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false,min:1,max:120,message:'输入长度应在1到120之间'}] }]"
                  placeholder="请输入关键字"
              />
            </a-form-item>
            <a-form-item>
              <a-button @click="query()" :loading="queryLoading">查询</a-button>
            </a-form-item>
          </a-form>
          <a-button type="primary" @click="showModal('添加')">添加数据字典</a-button>
        </a-space>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
        >
           <span slot="action" slot-scope="text">
              <a @click="updateItem(text.id)">编辑</a>
           </span>
        </a-table>
      </div>
    </a-card>

    <dictionary-name-modal :query="query" ref="dictsForm"></dictionary-name-modal>

  </div>
</template>

<script>
import * as dictionaryContents from "@/services/dictionaryContents"
import DictionaryNameModal from "@/pages/dictionary/dictionaryNameModal"
import validators from "@/utils/validators";
const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '名称',
    dataIndex: 'title',
    ellipsis: true,
  },
  {
    title: '编码',
    dataIndex: 'sn',
    ellipsis: true,
  },
  {
    title: '简介',
    dataIndex: 'intro',
    ellipsis: true,
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]
export default {
  name:"contents",
  components: {DictionaryNameModal},
  data() {
    return {
      validators,
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,


    }
  },
  created() {
    this.fetch()
  },
  methods: {
    query(){
      console.log("Is  query....");
      this.queryLoading = true
      this.queryForm.validateFields((err, values) => {
        if (err) {
          this.queryLoading = false
          console.log("form error");
          return;
        }
        this.fetch({"page": this.pagination.current, "size": 10,...values})
      })
    },
    // table
    handleTableChange(pagination) {
      const pager = {...this.pagination};
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        size: pagination.pageSize,
        page: pagination.current,
      });
    },

    fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      dictionaryContents.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.records.map((e, i) => ({key: i + "",...e}))
        this.pagination = pagination
        this.loading = false
        this.queryLoading = false
      })
    },

    deleteItem(text) {
      const title = '删除'
      dictionaryContents.deleteItem(text.id).then(({data})=>{
        if (data.code !== 200) {
          this.$notification['error']({
            message: title + '数据字典信息出现错误',
            description: '建议检查网络连接或重新登陆',
          });
        }
        this.$notification.success({
          message: title + '成功',
          description: title + '数据字典信息成功',
        });
        this.fetch({"page": this.pagination.current, "size": 10})
      })
    },
    updateItem(id) {
      this.showModal('更改',id)
    },
    // modal
    showModal(title,id) {
      this.$refs["dictsForm"].showModal(title,id);
    },

  }
}
</script>

<style lang="less" scoped>
.search {
  margin-bottom: 54px;
}

.fold {
  width: calc(100% - 216px);
  display: inline-block
}

.operator {
  margin-bottom: 18px;
}

@media screen and (max-width: 900px) {
  .fold {
    width: 100%;
  }
}
</style>
