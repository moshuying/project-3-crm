<template>
  <v-md-editor v-model="text"  :disabled-menus="[]"  @upload-image="handleUploadImage" height="400px"></v-md-editor>
</template>

<script>
export default {
  name: "workMkd",
  data() {
    return {
      text: '',
    };
  },
  methods: {
    handleUploadImage(event, fun, files)
    {
      // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
      console.log(event, fun,files);

      // 此处只做示例

    }
  }
}
</script>

<style scoped>

</style>