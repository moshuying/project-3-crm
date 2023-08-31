<template>
  <a-modal
      :title="title"
      :visible="visible"
      width="800px"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
      okText="提交"
  >
    <a-form :form="form" >

      <a-form-item hidden>
        <a-input v-decorator="['id',{ rules: [{ required: false}] }]"/>
      </a-form-item>

      <div v-for="(item) in baseColumns" :key="item.dataIndex">

        <a-form-item :label="item.title">

          <a-select v-if="item.dataIndex==='gender'"
                    style="width: 6rem"
                    v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">
            <a-select-option :value="1">
              男
            </a-select-option>
            <a-select-option :value="0">
              女
            </a-select-option>
          </a-select>

          <a-select v-else-if="item.dataIndex==='job'"
                    v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">
            <a-select-option v-for="(list) in dictionaryDetailsJob" :key="list.id">
              {{ list.title }}
            </a-select-option>
          </a-select>

          <a-select v-else-if="item.dataIndex==='source'"
                    v-decorator="[item.dataIndex,{ rules: [{ required: true, message: item.title }] }]">
            <a-select-option v-for="(list) in dictionaryDetailsSource" :key="list.id">
              {{ list.title }}
            </a-select-option>
          </a-select>
          <a-input-number v-else-if="item.dataIndex==='age'" :min="0" :max="200"
                          v-decorator="[item.dataIndex, { rules: [{ required: true, message: item.title  }]}]"/>
          <a-input v-else-if="item.dataIndex==='name'"
                   v-decorator="[item.dataIndex, { rules: [{ required: true,min:1,max:120,message:'输入长度应在1到120之间' }]}]"
                   :placeholder="`请输入`+item.title"
          />
          <a-input v-else-if="item.dataIndex==='tel'"
                   v-decorator="[item.dataIndex, { rules: [{ required: true,pattern:validators.phoneReg,message:validators.phoneMsg  }]}]"
                   :placeholder="`请输入`+item.title"
          />
          <a-input v-else-if="item.dataIndex==='qq'"
                   v-decorator="[item.dataIndex, { rules: [{ required: true,pattern:validators.qqReg,message:validators.qqMsg  }]}]"
                   :placeholder="`请输入`+item.title"
          />
          <a-input v-else
                   v-decorator="[item.dataIndex, { rules: [{ required: true, message: item.title  }]}]"
                   :placeholder="`请输入`+item.title"
          />
        </a-form-item>

      </div>
    </a-form>
  </a-modal>
</template>

<script>
import * as customerManager from "@/services/customerManager";
import validators from "@/utils/validators";
import * as dictionaryDetails from "@/services/dictionaryDetails";

export default {

  /**
   * @name: AddUpdateContacts  添加或者修改联系人
   * @author: Administrator
   * @date: 2022/10/9 18:54
   * @description：AddCustomer
   * @update: 2022/10/9 18:54
   */
  name: "AddUpdateContacts",

  props:{
    baseColumns:null,
    visible: null,
  },

  data() {
    return {
      // 新增修改
      validators,
      form: this.$form.createForm(this, {name: 'coordinated'}),
      confirmLoading: false,
      title: '新增',
      newContact: null, //新增s
     // visible: false,
      // 字典细节
      dictionaryDetailsJob:[],
    }

  },

  // 展示自定义字段；
  mounted() {
    dictionaryDetails.list({page:1,size:999999,id:1}).then(({data})=>{
        this.dictionaryDetailsJob = data.data.records
    });
  },

  methods: {

    handleOk() {
      this.confirmLoading = true;
      this.form.validateFields((err, values) => {
        if (err) {
          console.log("form error");
          this.confirmLoading=false
          return;
        }
        let method = 'add';
        if (values.id) method = 'update';
        customerManager[method](values).then(({data}) => {
          this.confirmLoading = false;
          if (data.code !== 200) {
            this.$notification['error']({
              message: this.title + '角色信息出现错误',
              description: '建议检查网络连接或重新登陆',
            });
          }else{
            this.$notification.success({
              message: this.title + '成功',
              description: this.title + '角色信息成功',
            });
          }
          this.visible = false
          this.$emit('query');
        })
      });
    },

    handleCancel() {
      this.visible = false;
      this.title = ''
      this.confirmLoading = false
      this.form.resetFields()
    },

    // modal
    async showModal(title) {
      this.visible = true;
      this.title = title || '新增'
      await this.form.resetFields()
    },

    updateItem1(id,line){
      console.log("updateItem1",id,line)
      this.updateItem(id,line)
    },

    //更新字段
    async updateItem(id,line) {
      this.confirmLoading=false
      await this.showModal('编辑联系人信息')
      customerManager.getDetail(id).then(({data}) => {
        if(!data.data) return;
        // 这里不能循环
        this.form.setFieldsValue({id:data.data["id"]})
        this.form.setFieldsValue({name:data.data["name"]})
        this.form.setFieldsValue({age:line["age"]})
        this.form.setFieldsValue({gender:data.data["gender"]})
        this.form.setFieldsValue({tel:data.data["tel"]})
        this.form.setFieldsValue({qq:data.data["qq"]})
        this.form.setFieldsValue({job:data.data["job"]})
        this.form.setFieldsValue({source:data.data["source"]})
      })
    },
  }

}
</script>

<style scoped>

</style>