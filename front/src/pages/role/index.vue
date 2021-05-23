<template>
  <div>
    <a-card>
      <div>
        <a-space class="operator">
          <a-button type="primary" @click="showModal('新增')">新增</a-button>
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
      <a-form :form="form">
        <a-form-item hidden>
          <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
        </a-form-item>
        <a-form-item label="角色名称">
          <a-input
              v-decorator="['name', { rules: [{ required: true, validator:validators.length({min:1,max:50}) }] }]"
          />
        </a-form-item>
        <a-form-item label="角色编号">
          <a-input
              v-decorator="['sn',{ rules: [{ required: true,validator:validators.length({min:1,max:50})  }] },]"
              placeholder="请输入角色编号"
          />
        </a-form-item>
        <a-form-item label="角色权限">
          <a-transfer
              v-decorator="['permissions',{ rules: [{ required: this.title==='新增' }] },]"
              :operations="['加入权限', '移除权限']"
              :data-source="permissionList"
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
import * as role from "@/services/role"
import * as permission from "@/services/permission"
import difference from 'lodash/difference';
import validators from "@/utils/validators";

const columns = [
  {
    title: '编号',
    dataIndex: 'id'
  },
  {
    title: '角色名称',
    dataIndex: 'name',
  },
  {
    title: '角色编号',
    dataIndex: 'sn',
  },
  {
    title: '操作',
    scopedSlots: {customRender: 'action'}
  }
]
const permissionTableColumns = [
  {
    dataIndex: 'name',
    title: '权限名称',
    ellipsis: true,
  },
  {
    dataIndex: 'expression',
    title: '权限表达式',
    ellipsis: true,
  },
];
export default {
  name: 'Department',
  data() {
    return {
      validators,
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
      permissionList: [],
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
  },
  methods: {
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
      role.list(params || {"page": 1, "size": 10}).then(({data}) => {
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
      role.deleteItem(text.id).then(({data}) => {
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
      this.permissionList = []
      this.reloadId = id
      await this.showModal('更改')
      role.getDetail(id).then(({data}) => {
        // 这里不能循环
        this.form.setFieldsValue({"id": data.data["id"]})
        this.form.setFieldsValue({"sn": data.data["sn"]})
        this.form.setFieldsValue({"name": data.data["name"]})
        const {permissions} = data.data
        if (!permissions) return;

        for(let i=0;i<permissions.length;i++){
          const ps = permissions[i];
          this.targetKeys.push(this.permissionList.find(e=>e.id===ps.id).key)
        }
      })
    },
    // modal
    async showModal(title) {
      this.visible = true;
      this.title = title || '新增'
      this.form.resetFields()
      this.targetKeys = []
      await this.getAllPermissionList()
    },
    async handleOk() {
      this.confirmLoading = true;
      this.form.validateFields(async (err, values) => {
        if (err) {
          this.confirmLoading = false;
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';
        if(values.permissions && values.permissions.length>=1){
          let arr =[]
          for(let i=0;i<values.permissions.length;i++){
            const e = values.permissions[i]
            arr.push({
              id:this.permissionList[e].id,
              name:this.permissionList[e].name,
              expression:this.permissionList[e].expression
            })
          }
          values.permissions = arr
        }
        const {data} = await role[method](values)
        this.confirmLoading = false;
        if (data["code"] !== 200) {
          this.$notification['error']({
            message: this.title + '角色信息出现错误',
            description: data.message || '建议检查网络连接或重新登陆',
          });
        } else {
          this.$notification.success({
            message: this.title + '成功',
            description: this.title + '角色信息成功',
          });
          this.visible = false
          this.form.resetFields()
          this.getAllPermissionList()
          this.fetch({"page": this.pagination.current, "size": 10})
        }
      });
    },
    handleCancel() {
      this.visible = false;
      this.title = ''
      this.confirmLoading = false
      this.form.resetFields()
    },
    // modal transfer
    getAllPermissionList() {
      return permission.list({page: 1, size: 999999999}).then(({data}) => {
        this.permissionList = data.data.list.map((e, i) => ({key: i + "", title: e.name, ...e}))
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
