<template>
  <div>


  <a-descriptions title="系统配置属性" :size="size">
    <a-descriptions-item label="Product">
      Cloud Database
    </a-descriptions-item>
    <a-descriptions-item label="Billing">
      Prepaid
    </a-descriptions-item>
    <a-descriptions-item label="Time">
      18:00:00
    </a-descriptions-item>
    <a-descriptions-item label="Amount">
      $80.00
    </a-descriptions-item>
    <a-descriptions-item label="Discount">
      $20.00
    </a-descriptions-item>
    <a-descriptions-item label="Official">
      $60.00
    </a-descriptions-item>
  </a-descriptions>
    <a-divider></a-divider>
  <a-descriptions title="自定义属性" :size="size">
    <a-descriptions-item label="Product">
      Cloud Database
    </a-descriptions-item>
    <a-descriptions-item label="Billing">
      Prepaid
    </a-descriptions-item>
    <a-descriptions-item label="Time">
      18:00:00
    </a-descriptions-item>
    <a-descriptions-item label="Amount">
      $80.00
    </a-descriptions-item>
    <a-descriptions-item label="Discount">
      $20.00
    </a-descriptions-item>
    <a-descriptions-item label="Official">
      $60.00
    </a-descriptions-item>
  </a-descriptions>

  </div>
</template>

<script>

export default {

  name: "entAdvancedProps",
  data() {
    return {
      size: 'default',
    };
  }
}
</script>

<style scoped>

</style>