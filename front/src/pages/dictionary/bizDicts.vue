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
        <a-button type="primary" @click="showModal('添加')">添加业务表单</a-button>
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
</div>
</template>

<script>
export default {
  /**
   * @name: bizDicts (业务字典集合)
   * @author: Administrator
   * @date: 2023/5/23 11:16
   * @description：bizDicts
   * @update: 2023/5/23 11:16
   */
  name: "bizDicts"
}
</script>

<style scoped>

</style>