<template>
  <div style="display: flex;">
    <a-card style="width: 30%" title="系统字典组">
      <a slot="extra" @click="showDicModal('添加')">添加</a>
      <div :key="item.id" v-for="(item) in dContentsList">
        <div
            :style="{backgroundColor:leftFirstId===item.id?'#337ab7':''}"
            @click="changeRight(item)">
          <a-button
              :style="{color:leftFirstId===item.id?'white':''}"
              type="link">{{item.title}}</a-button>
        </div>
      </div>
    </a-card>
    <a-card style="width: 100%">
      <div style="width: 100%;">
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
          <a-button type="primary" @click="showModal('添加')">添加字典明细</a-button>
          <a-button type="primary" @click="showBizModal('添加')">添加到业务表单</a-button>
        </a-space>
      </div>
      <div>
        <a-tag color="red">id: {{selectedDic.id}}</a-tag>
        <a-tag color="red">sn: {{selectedDic.sn}}</a-tag>
      </div>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
        >
           <span slot="action" slot-scope="text">
              <a @click="updateItem(text.id)">编辑 </a> |

             <a-popover v-model="text.visible" title="你确定要删除？" trigger="click">
                <a slot="content" @click="deleteItem(text)">确定</a>
                <a-button type="link">
                  删除
                </a-button>
             </a-popover>

           </span>

        </a-table>
    </a-card>
<!--     添加 明细   -->
    <a-modal
        :title="title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        okText="提交"
    >
      <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
        <a-form-item hidden>
          <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item hidden>
          <a-input v-decorator="['parentid',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item label="字典明细名称">
          <a-input
              v-decorator="['title', { rules: [{ required: true, min:1,max:125,message:'内容长度在1到125之间' }] }]"
          />
        </a-form-item>
        <a-form-item label="字典明细序列">
          <a-input-number
              :min="0"
              :max="999"
              v-decorator="['sequence',{ rules: [{ required: true, message: '请输入字典明细序列' }] },]"
              placeholder="请输入字典明细序列"
          />
        </a-form-item>
      </a-form>
    </a-modal>


    <!--  添加到业务表单  -->
    <a-modal
        title="添加到业务表单"
        width="80%"
        :visible="visibleBiz"
        :confirm-loading="confirmLoading"
        @ok="handleBizOk"
        @cancel="handleBizCancel"
        okText="提交"
    >
      <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
        <a-list  :data-source="bizData">
          <a-list-item slot="renderItem" slot-scope="item">
            <a-card :title="item.tableName">
              共有：{{item.schemaJson.length}} <br>
              <a-tag  closable @close="() => delBizItem(dic, item)" color="orange" :key="key" v-for="(dic,key) in item.schemaJson" >
                {{ dic.title }}
              </a-tag>
              <br>
              <a-button type="link" @click="addtoBiz(item)">
                添加
              </a-button>
            </a-card>

          </a-list-item>
        </a-list>
      </a-form>

    </a-modal>


    <dictionary-name-modal :query="fetchDictionaries" ref="dictsForm"></dictionary-name-modal>
  </div>
</template>

<script>
import * as dictionaryDetails from "@/services/dictionaryDetails"
import * as dictionaryContents from "@/services/dictionaryContents"
import DictionaryNameModal from "@/pages/dictionary/dictionaryNameModal"
import validators from "@/utils/validators";
import * as dictionaryBizRef from "@/services/dictionaryBizRef";
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
    title: '序列',
    dataIndex: 'sequence',
    ellipsis: true,
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]


export default {
  components:{DictionaryNameModal},
  data() {
    return {
      validators,
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
      // table
      columns: columns,
      bizData: [],
      dataSource: [],
      selectedRows: [],
      pagination: {},
      loading: false,
      // modal
      title: '添加',
      visible: false,
      visibleBiz:false,
      confirmLoading: false,
      dContentsList:[],
      // modal form
      form: this.$form.createForm(this, {name: 'coordinated'}),
      // left card
      leftFirstId:null,
      selectedDic:null,
    }
  },

  created() {

    this.fetchDictionaries().then(()=>{
      this.fetch({page:1,size:10,id:this.leftFirstId})
    })

  },
  methods: {

    fetchDictionaries(){
      return dictionaryContents.list({page:1,size:999999}).then(({data})=>{
        this.dContentsList = data.data.records
        this.leftFirstId = this.dContentsList[0].id
        this.selectedDic = this.dContentsList[0]
      })
    },

    changeRight(item){
      this.leftFirstId = item.id
      this.selectedDic = item
      this.query()
    },

    // 搜索
    query(){
      this.queryLoading = true
      this.queryForm.validateFields((err, values) => {
        if (err) {
          this.queryLoading = false
          console.log("form error");
          return;
        }
        this.fetch({"page": this.pagination.current, "size": 10,id:this.leftFirstId,...values})
      })
    },
    // table
    handleTableChange(pagination) {
      const pager = {...this.pagination};
      pager.current = pagination.current;
      this.pagination = pager;
      this.query()
    },

    //加载明细
    fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      dictionaryDetails.list(params || {"page": 1, "size": 10,id:this.leftFirstId}).then(({data}) => {
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
      dictionaryDetails.deleteItem(text.id).then(({data})=>{
        if (data.code !== 200) {
          this.$notification['error']({
            message: title + '字典明细信息出现错误',
            description: '建议检查网络连接或重新登陆',
          });
        }
        this.$notification.success({
          message: title + '成功',
          description: title + '字典明细信息成功',
        });

        text.visible = false;

        this.fetch({"page": this.pagination.current, "size": 10,id:this.leftFirstId})
      })
    },

    updateItem(id) {
      this.showModal('更改')
      dictionaryDetails.getDetail(id).then(({data}) => {
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"sequence": data.data["sequence"]})
        this.form.setFieldsValue({"title": data.data["title"]})
        this.form.setFieldsValue({"parentid": data.data["parentid"]})
      })
    },
    // modal
    showModal(title = '添加') {
      this.visible = true;
      this.title = title || '添加'
      this.form.resetFields()
      this.$nextTick(()=>{
        this.form.setFieldsValue({"parentid": this.leftFirstId})
      })
    },

    showBizModal(){
      this.visibleBiz = true;
      dictionaryBizRef.list().then(({data}) => {
        const res = data.data
        this.bizData = res.records
        console.log(res);
      });


    },

    //添加字典
    showDicModal(){
      this.$refs["dictsForm"].showModal();
    },

    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.confirmLoading = true;
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';
        dictionaryDetails[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '字典明细信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '字典明细信息成功',
            });
          }
          this.visible = false
          this.fetch({"page": this.pagination.current, "size": 10,id:this.leftFirstId})
        })
      });
    },
    handleCancel() {
      this.visible = false;
      this.title=''
      this.confirmLoading = false
      this.form.resetFields()
    },


    handleBizOk(){
      this.visibleBiz = false;
    },

    handleBizCancel(){
      this.visibleBiz = false;
    },

    // 添加字段到业务表单
    addtoBiz(bizForm){
      if(!bizForm.schemaJson){
        bizForm.schemaJson =[]
      }

      let has  = bizForm.schemaJson.find(item =>{
        return item.id === this.selectedDic.id;
      })
      console.log("has",has)
      if(!has){
        bizForm.schemaJson.push(this.selectedDic)
        dictionaryBizRef.add(bizForm).then(({data}) => {
          console.log("dictionaryBizRef",data);
        })
      }else{
        this.$message.warning('已经有该字段了');
        console.log("已经有该字段了")
      }
    },

    delBizItem(dic, bizForm){
      console.log(dic, bizForm)
      bizForm.schemaJson.forEach((ele, index) =>{
        if(ele.id === dic.id){
          bizForm.schemaJson.splice(index,1)
        }
      });

      console.log("after del:", bizForm.schemaJson)

      dictionaryBizRef.add(bizForm).then(({data}) => {
        console.log("delBizItem",data);
      })

    }


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
