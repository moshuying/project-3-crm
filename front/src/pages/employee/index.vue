<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false,validator:validators.length({min:0,max:120})}] }]"
                  placeholder="请输入姓名/邮箱"
              />
            </a-form-item>
            <a-form-item label="员工部门">
              <a-select
                  style="width: 6rem"
                  v-decorator="['dept',{ rules: [{ required: true, message: '员工部门' }] }]">
                <a-select-option :value="0">
                  全部
                </a-select-option>
                <a-select-option
                    :value="item.id"
                    :key="item.id"
                    v-for="(item) in departmentNames">
                  {{item.name}}
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item>
              <a-button :loading="queryLoading" @click="query()">查询</a-button>
            </a-form-item>
          </a-form>
          <a-button type="success" @click="showModal('新增')">添加</a-button>
          <a-button type="primary" @click="mDelete()">批量删除</a-button>
          <a-popconfirm
              title="您确定导出当前搜索条件下的所有结果么？"
              ok-text="是"
              cancel-text="否"
              @confirm="exportFile()"
          >
            <a-button icon="download" type="primary">导出</a-button>
          </a-popconfirm>
          <a-upload :showUploadList="false" :file-list="fileList" :remove="handleRemove" :before-upload="beforeUpload">
            <a-button> <a-icon type="upload" /> 导入数据</a-button>
          </a-upload>
          <a-button icon="download" @click="downloadTemplate" type="link">导入模板</a-button>
        </a-space>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
            :row-selection="{selectedRowKeys: outSelectedRowKeys,onChange: onSelectChange }"
            :loading="loading"
            @change="handleTableChange"
        >
           <span slot="action" slot-scope="text">
              <a @click="updateItem(text.id)">编辑</a> |
               <a-popconfirm
                   title="您真的要删除这行数据么？"
                   ok-text="是"
                   cancel-text="否"
                   @confirm="deleteItem(text)"
               >
              <a>删除</a>
              </a-popconfirm>
           </span>
        </a-table>
      </div>
    </a-card>
    <a-modal
        :title="title"
        :visible="visible"
        :confirm-loading="confirmLoading"
        @ok="handleOk"
        @cancel="handleCancel"
        okText="提交"
        width="80%"
    >
      <a-form :form="form" :layout="`horizontal`">
        <a-form-item hidden>
          <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item label="员工名称">
          <a-input
              v-decorator="['name', { rules: [{ required: true,validator:validators.length({min:3,max:20})}] }]"
          />
        </a-form-item>
        <a-form-item label="员工密码">
          <a-input
              v-decorator="['password', { rules: [{ required: title==='新增', validator: title==='新增' && validators.password() }] }]"
          />
        </a-form-item>
        <a-form-item label="验证密码">
          <a-input
              v-decorator="['rePassword', { rules: [{ required: title==='新增',validator: title==='新增' && checkRePassword }] }]"
          />
        </a-form-item>
        <a-form-item label="员工年龄">
          <a-input-number
              :max="200"
              :min="1"
              v-decorator="['age',{ rules: [{ required: true, message: '请输入员工年龄' }] }]"
              placeholder="请输入员工年龄"
          />
        </a-form-item>
        <a-form-item label="员工email">
          <a-input
              v-decorator="['email',{ rules: [{ required: true, validator:validators.email() }] }]"
              placeholder="请输入员工email"
          />
        </a-form-item>
        <a-form-item label="员工部门">
          <a-select
              v-decorator="['dept',{ rules: [{ required: true, message: '请输入员工部门' }] }]">
            <a-select-option
                :value="item.id"
                :key="item.id"
                v-for="(item) in departmentNames">
              {{item.name}}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="超级管理员">
          <a-checkbox :checked="form.getFieldValue('admin')" v-decorator="['admin',{ rules: [{ required: false }] }]">
          </a-checkbox>
        </a-form-item>
        <a-form-item label="员工角色">
          <a-transfer
              v-decorator="['roleIds',{ rules: [{ required: false }] }]"
              :operations="['加入角色', '移除角色']"
              :data-source="roleIds"
              :target-keys="targetKeys"
              :disabled="disabled"
              :show-search="showSearch"
              :filter-option="(inputValue, item) => item.title.indexOf(inputValue) !== -1"
              :show-select-all="false"
              @change="handleChange"
          >
            <template
                slot="children"
                slot-scope="{
                props: { direction, filteredItems, selectedKeys, disabled: listDisabled },
                on: { itemSelectAll, itemSelect },
              }"
            >
              <a-table
                  :row-selection="getRowSelection({ disabled: listDisabled, selectedKeys, itemSelectAll, itemSelect })"
                  :columns="direction === 'left' ? leftColumns : rightColumns"
                  :data-source="filteredItems"
                  size="small"
                  :style="{ pointerEvents: listDisabled ? 'none' : null }"
                  :custom-row="({ key, disabled: itemDisabled }) => ({
                  on: {
                    click: () => {
                      if (itemDisabled || listDisabled) return;
                      itemSelect(key, !selectedKeys.includes(key));
                    },
                  },
                })"
              />
            </template>
            <a-button
                slot="footer"
                size="small"
                style="float:right;margin: 5px"
                @click="updateItem(reloadId)"
            >
              刷新所有权限
            </a-button>
            <span slot="notFoundContent">
              没数据
            </span>
          </a-transfer>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import * as employee from "@/services/employee"
import * as role from "@/services/role"
import * as department from "@/services/department"
import difference from 'lodash/difference';
import * as XLSX from 'xlsx'
import validators from "@/utils/validators";

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '名称',
    dataIndex: 'name',
    ellipsis: true,
  },
  {
    title: 'email',
    dataIndex: 'email',
    ellipsis: true,
  },
  {
    title: '年龄',
    dataIndex: 'age',
  },
  {
    title: '部门',
    dataIndex: 'departmentName',
    ellipsis: true,
  },
  {
    title: '角色',
    dataIndex: 'roleNames',
    ellipsis: true,
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]
const permissionTableColumns = [
  {
    title: '角色名称',
    dataIndex: 'name',
  },
  {
    title: '角色编号',
    dataIndex: 'sn'
  },
];
export default {
  name: 'Department',
  data() {
    return {
      validators,
      // 上传
      fileList: [],
      uploading: false,
      // 外层table 多选
      outSelectedRowKeys:[],
      outSelectedRows:[],
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      queryLoading:false,
      departmentNames:[],
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {current:1},
      loading: false,
      // modal
      title: '新增',
      visible: false,
      confirmLoading: false,
      reloadId: null,
      // modal form
      form: this.$form.createForm(this, {name: 'coordinated'}),
      // modal permission list
      roleIds: [],
      targetKeys: [],
      selectedKeys: [],
      disabled: false,
      showSearch: false,
      leftColumns: permissionTableColumns,
      rightColumns: permissionTableColumns,
    }
  },
  authorize: {
    deleteRecord: 'delete'
  },
  created() {
    department.list({"page": 1, "size": 99999}).then(({data})=>{
      this.departmentNames = data.data.list.map((e,i)=>({key:i+'',...e}))
    })
    this.getAllroleIds()
  },
  mounted() {
    this.queryForm.setFieldsValue({"dept":0})
    this.fetch()// 这里不能query
  },
  methods: {
    // 上传
    handleRemove(file) {
      const index = this.fileList.indexOf(file);
      const newFileList = this.fileList.slice();
      newFileList.splice(index, 1);
      this.fileList = newFileList;
    },
    async beforeUpload(file) {
      this.fileList = [...this.fileList, file];
      // const xlsxRead = XLSX.read(file)
      let res = await new Promise((resolve) => {
        let fileReader = new FileReader()
        fileReader.readAsBinaryString(file)
        fileReader.onload=e=>{
          /* Parse data */
          const bstr = e.target.result;
          const wb = XLSX.read(bstr, {type:'binary'});
          /* Get first worksheet */
          const wsname = wb.SheetNames[0];
          const ws = wb.Sheets[wsname];
          /* Convert array of arrays */
          const data = XLSX.utils.sheet_to_json(ws, {header:1});
          const sLen =6
          if(data[0].length!==sLen){
            resolve("数据内容不合法，请先导出数据当作模板！")
          }
          for(let i=1;i<data.length;i++){
            for(let j=0;j<data[i].length;j++){
              if(data[i].length!==sLen){
                resolve(`第${i}行数据内容不足！`)
                break;
              }
              const booleanCheck = Object.prototype.toString.call(data[i][j])
              if(data[i][j]==='' || booleanCheck === 'Null' || booleanCheck === '[object Undefined]'){
                resolve(`第${i+1}行${j+1}列数据内容不合法！`)
              }
            }
          }

          const json =[]
          for(let i=1;i<data.length;i++){
            const obj = {}
            for(let k=0;k<data[i].length;k++){
              const value = data[i][k].trim()
              if(data[0][k]==='角色') {
                const roleIds = this.roleIds
                const roles = value.split(',')
                const find=[]
                roles.forEach(name => {
                  const r = roleIds.find(e => e.name === name)
                  if (r) {
                    find.push(r.id)
                  }else {
                    resolve(`第${i+1}行${k+1}列数据内容不合法！无法找到此角色！请校验数据并刷新页面后重新导入！`)
                  }
                })
                obj.roleIds = find
              }else if(data[0][k]==='部门'){
                const find =this.departmentNames.find(e=>e.name===value)
                if('[object Undefined]' === Object.prototype.toString.call(find)){
                  resolve(`第${i+1}行${k+1}列数据内容不合法！无法找到此部门！请校验数据并刷新页面后重新导入！`)
                }
                obj.dept = find.id
              }else if(data[0][k]==='密码'){
                const msg = validators.password()(null,value)
                if(msg){
                  resolve(`第${i+1}行${k+1}列数据内容不合法!${msg}`)
                }
                obj.password = value
              }else if(data[0][k]==='密码'){
                const msg = validators.email()(null,value)
                if(msg){
                  resolve(`第${i+1}行${k+1}列数据内容不合法!${msg}`)
                }
                obj.password = value
              }else {
                switch (data[0][k]){
                  case '名称':obj.name = value;break;
                  case '年龄':obj.age = value ;break;
                  case 'Email': obj.email = value;break;
                }
              }
            }
            json.push(obj)
          }
         resolve(json)
        }
      })
      if( "[object Array]" !== Object.prototype.toString.call(res)){
        this.$notification.warning({
          message: res,
          description: '建议检查您所上传的文件内容',
        })
      }else{
        for(let i=0;i<res.length;i++){
          const {data} = await employee.add(res[i])
          if(data.code!==200){
            this.$notification.error({message: `第${i+1}条信息导入失败`, description: data.message||"请检查您的网络，不建议一次上传过多内容"})
            break;
          }else{
            this.$notification.success({message: "导入成功"})
          }
        }
      }
      this.query()
      return false;
    },
    // excel
    async exportFile(){
      this.queryForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          return;
        }
        this.fetch({"page": 1, "size": 99999999,...values}).then(({data})=>{
          const {list} = data
          const res = list.map(e=>({
            '部门':e.departmentName,
            '名称':e.name,
            '角色':e.roleNames,
            '年龄':e.age,
            'Email':e.email
          }))
          /* convert state to workbook */
          const ws = XLSX.utils.json_to_sheet(res);
          const wb = XLSX.utils.book_new();
          XLSX.utils.book_append_sheet(wb, ws, "SheetJS");
          /* generate file and send to client */
          XLSX.writeFile(wb, "员工信息.xlsx");
        })
      })
    },
    downloadTemplate(){
      const res = [
        {
          '部门':'test部门',
          '名称':'test名称',
          '角色':'test角色',
          '密码':'testPassword',
          '年龄':'18',
          'Email':'c@c.c'
        }
      ]
      /* convert state to workbook */
      const ws = XLSX.utils.json_to_sheet(res);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "SheetJS");
      /* generate file and send to client */
      XLSX.writeFile(wb, "员工信息模板.xlsx");
    },
    onSelectChange(selectedRowKeys,selectedRows) {
      this.outSelectedRowKeys = selectedRowKeys;
      this.outSelectedRows = selectedRows;
    },
    mDelete(){
      if(this.outSelectedRows.length<=0){
        this.$message.warning("尚未批量选择")
      }else {
        let arr = []
        for(let i=0;i<this.outSelectedRows.length;i++){
          arr.push(employee.deleteItem(this.outSelectedRows[i].id))
        }
        Promise.all(arr).then(()=>{
          this.$notification.success({
            message: '删除成功！',
          });
          this.fetch()
        }).catch(()=>{
          this.$notification['error']({
            message: '删除失败！',
            description: '建议检查网络连接或重新登陆',
          });
        })
      }
    },
    query(){
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
      this.outSelectedRowKeys=[]
      this.outSelectedRows =[]
      const pager = {...this.pagination};
      pager.current = pagination.current;
      this.pagination = pager;
      this.query()
    },
    async fetch(params = {"page": 1, "size": 10}) {
      this.loading = true
      let {data} = await employee.list(params || {"page": 1, "size": 10})
      const res = data.data
      const pagination = {...this.pagination};
      pagination.total = res.total
      pagination.current = res.pageNum
      if(res.list){
        this.dataSource = res.list.map((e, i) => ({key: i + "", ...e}))
      }
      this.pagination = pagination
      this.loading = false
      this.queryLoading=false
      return data
    },
    deleteItem(text) {
      const title = '删除'
      employee.deleteItem(text.id).then(({data}) => {
        if (data.code !== 200) {
          this.$notification['error']({
            message: title + '角色信息出现错误',
            description: '建议检查网络连接或重新登陆',
          });
        }
        this.$notification.success({
          message: title + '成功',
          description: title + '角色信息成功',
        });
        this.fetch({"page": this.pagination.current, "size": 10})
      })
    },
    async updateItem(id) {
      if(this.confirmLoading){
        this.confirmLoading=false
      }
      this.targetKeys = []
      this.roleIds = []
      this.reloadId = id
      await this.showModal('更改')
      employee.getDetail(id).then(({data}) => {
        if(!data.data) return;
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"name": data.data["name"]})
        this.form.setFieldsValue({"admin": data.data["admin"] === 1})
        this.form.setFieldsValue({"age": data.data["age"]})
        this.form.setFieldsValue({"dept": data.data["dept"]})
        this.form.setFieldsValue({"email": data.data["email"]})
        const {roleIds} = data.data
        if (!roleIds) return;
        for(let i=0;i<roleIds.length;i++){
          const find = this.roleIds.find(e=>roleIds[i]==(e.id))
          this.targetKeys.push(find.key)
        }
      })
    },
    // modal
    async showModal(title) {
      this.visible = true;
      this.title = title || '新增'
      this.form.resetFields()
      this.targetKeys = []
      await this.getAllroleIds()
    },
    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.confirmLoading = false
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';
        if(!values.roleIds){
          delete values.roleIds
        }
        if(values.roleIds && values.roleIds.length>=1){
          let arr =[]
          for(let i=0;i<values.roleIds.length;i++){
            const e = values.roleIds[i]
            arr.push(this.roleIds[e].id)
          }
          values.roleIds = arr
        }
        employee[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '角色信息出现错误',
              description: data.message,
            });
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '角色信息成功',
            });
            this.visible = false
          }
          this.query()
        })
      });
    },
    handleCancel() {
      this.visible = false;
      this.title = ''
      this.confirmLoading = false
      this.form.resetFields()
    },
    checkRePassword(rule, value, callback) {
      if (value !== this.form.getFieldValue('password')) {
        callback('两次密码输入不一致!');
      }else {
        callback()
      }
    },
    // modal transfer
    getAllroleIds() {
      return role.list({page: 1, size: 999999999}).then(({data}) => {
        this.roleIds = data.data.list.map((e, i) => ({key: i + "", title: e.name, ...e}))
      })
    },
    handleChange(targetKeys) {
      this.targetKeys = targetKeys;
    },
    handleSelectChange(sourceSelectedKeys, targetSelectedKeys) {
      this.selectedKeys = [...sourceSelectedKeys, ...targetSelectedKeys];
    },
    getRowSelection({disabled, selectedKeys, itemSelectAll, itemSelect}) {
      return {
        getCheckboxProps: item => ({props: {disabled: disabled || item.disabled}}),
        onSelectAll(selected, selectedRows) {
          const treeSelectedKeys = selectedRows
              .filter(item => !item.disabled)
              .map(({key}) => key);
          const diffKeys = selected
              ? difference(treeSelectedKeys, selectedKeys)
              : difference(selectedKeys, treeSelectedKeys);
          itemSelectAll(diffKeys, selected);
        },
        onSelect({key}, selected) {
          itemSelect(key, selected);
        },
        selectedRowKeys: selectedKeys,
      };
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
