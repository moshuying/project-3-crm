<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-form layout="inline" :form="queryForm">
            <a-form-item label="关键字">
              <a-input
                  v-decorator="['keyword', { rules: [{ required: false}] }]"
                  placeholder="请输入姓名/邮箱"
              />
            </a-form-item>
            <a-form-item label="员工部门">
              <a-select
                  style="width: 6rem"
                  initial-value="0"
                  v-decorator="['dept',{ rules: [{ required: true, message: '员工部门' }] }]">
                <a-select-option value="0">
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
              <a-button @click="query()">查询</a-button>
            </a-form-item>
          </a-form>
          <a-button type="success" @click="showModal('新增')">添加</a-button>
          <a-button type="primary" >批量删除</a-button>
          <a-button type="primary" >导出</a-button>
          <a-button type="primary" >导入数据</a-button>
        </a-space>
        <a-table
            :columns="columns"
            :data-source="dataSource"
            :pagination="pagination"
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
              v-decorator="['name', { rules: [{ required: true, message: '请输入员工名称' }] }]"
          />
        </a-form-item>
        <a-form-item label="员工密码">
          <a-input
              v-decorator="['password', { rules: [{ required: true, message: '请输入员工密码' }] }]"
          />
        </a-form-item>
        <a-form-item label="验证密码">
          <a-input
              v-decorator="['rePassword', { rules: [{ required: true,validator:checkRePassword }] }]"
          />
        </a-form-item>
        <a-form-item label="员工年龄">
          <a-input-number
              v-decorator="['age',{ rules: [{ required: true, message: '请输入员工年龄' }] }]"
              placeholder="请输入员工年龄"
          />
        </a-form-item>
        <a-form-item label="员工email">
          <a-input
              v-decorator="['email',{ rules: [{ required: true, message: '请输入员工email' }] }]"
              placeholder="请输入员工email"
          />
        </a-form-item>
        <a-form-item label="员工部门">
          <a-select
              v-decorator="['dept',{ rules: [{ required: true, message: '员工部门' }] }]">
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

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '名称',
    dataIndex: 'name',
  },
  {
    title: 'email',
    dataIndex: 'email',
  },
  {
    title: '年龄',
    dataIndex: 'age',
  },
  {
    title: '部门',
    dataIndex: 'departmentName',
  },
  {
    title: '角色',
    dataIndex: 'roleNames',
    customRender:(text)=>text.toString(',')
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
      queryForm:this.$form.createForm(this, {name: 'coordinated'}),
      departmentNames:[],
      // table
      columns: columns,
      dataSource: [],
      selectedRows: [],
      pagination: {},
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
    this.fetch()
    department.list({"page": 1, "size": 99999}).then(({data})=>{
      this.departmentNames = data.data.list.map((e,i)=>({key:i+'',...e}))
    })
  },
  methods: {
    query(){
      this.queryLoading = true
      this.queryForm.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          return;
        }
        this.fetch({"page": 1, "size": 10,...values})
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
      employee.list(params || {"page": 1, "size": 10}).then(({data}) => {
        const res = data.data
        const pagination = {...this.pagination};
        pagination.total = res.total
        pagination.current = params.page
        this.dataSource = res.list.map((e, i) => ({key: i + "", ...e}))
        this.pagination = pagination
        this.loading = false
      })
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
        this.form.setFieldsValue({"password": data.data["password"]})
        const {roleIds} = data.data
        if (!roleIds) return;
        for(let i=0;i<roleIds.length;i++){
          const find = this.roleIds.find(e=>roleIds[i]===(e.id+""))
          this.targetKeys.push(find.key)
        }
        console.log(this.targetKeys)
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
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';
        if(values.roleIds.length>=1){
          let arr =[]
          for(let i=0;i<values.roleIds.length;i++){
            const e = values.roleIds[i]
            arr.push(this.roleIds[e].id)
          }
          values.roleIds = arr
        }
        console.log(values)
        employee[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '角色信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }
          this.$notification.success({
            message: this.title + '成功',
            description: this.title + '角色信息成功',
          });
          this.visible = false
          this.fetch({"page": this.pagination.current, "size": 10})
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
      console.log(value,this.form.getFieldValue('password'))
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
