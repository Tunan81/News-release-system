<template>
  <div class='center'>
    <img src='../assets/notfound.gif' alt='' />
  </div>
</template>

<script>
export default {
  name: "404.vue"
}
</script>

<style scoped>
.center {
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

img {
  width: 300px;
}
</style>