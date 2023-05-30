<template>
  <a-modal
      title="Title"
      :visible="visible"
      :confirm-loading="confirmLoading"
      :zIndex="99999"
      :width="1080"
      @ok="handleOk"
      @cancel="handleCancel"
  >

    <a-row>encodeUrl
      <a-col :span="20">
        <iframe src="https://aiqicha.baidu.com/s?q=" ></iframe>
      </a-col>
      <a-col :span="4">
        <FormulateForm @submit="handAddCusEnt"
                       v-model="values"
                       :schema="schema"

        />
      </a-col>
    </a-row>

  </a-modal>
</template>

<script>
export default {
  /**
   * @name: queryEntModal
   * @author: Administrator
   * @date: 2023/5/29 12:05
   * @description：queryEntModal
   * @update: 2023/5/29 12:05
   */
  name: "queryEntModal",
  data() {
    return {
      values:null,
      ModalText: 'Content of the modal',
      visible: false,
      confirmLoading: false,
      encodeUrl: null, //+encodeURIComponent(this.values.entName)+"&t=0"
      schema: [
        {
          name: 'entName',
          type: 'text',
          label: '企业名称',
          validation: 'required',
          placeholder: "请填写企业名称",
        },
        {
          type: 'button',
          label: '爱企查验证',
          "@click": true,
        },
        {
          type: 'text',
          name: 'entAddr',
          label: '企业地址',
        },
        {
          type: 'text',
          name: 'entWebsite',
          label: '企业网站',
          validation: "required",
          help: "url地址"
        },

        {
          type: 'submit',
          label: '确认添加',
          name: 'submit',
        }
      ],
    };
  },
  methods: {

    showModal() {
      this.visible = true;
    },
    handleOk() {
      this.ModalText = 'The modal will be closed after two seconds';
      this.confirmLoading = true;
      setTimeout(() => {
        this.visible = false;
        this.confirmLoading = false;
      }, 2000);
    },
    handleCancel() {
      console.log('Clicked cancel button');
      this.visible = false;
    },

    handAddCusEnt(){
      console.log();
    }
  },
}
</script>

<style scoped>

</style>