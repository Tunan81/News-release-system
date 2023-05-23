<template>
  <div>
    <h1 style="text-align: center">测试自定义富文本组件</h1>
    <MyQuillEditor></MyQuillEditor>
  </div>
</template>

<script>
import MyQuillEditor from "@/components/myQuillEditor.vue";

export default {
  name: "test",
  components: {
    MyQuillEditor
  },
}
</script>

<style scoped>

</style>