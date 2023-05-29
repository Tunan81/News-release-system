<template>
  <div class='center'>
    <img src='../assets/icon/404.png' alt='' class="load-fade"/>
    <p>抱歉！你访问的页面不存在或已删除 :( </p>
    <span class="back" @click="go()">返回上页</span>
  </div>
</template>

<script>
export default {
  name: "404.vue",
  methods: {
    go: function () {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
.center {
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.load-fade {
  opacity: 1;
  animation-name: fade;
  animation-duration: 5s; /* 淡入时间 */
  animation-timing-function: linear;
  animation-direction: alternate;
}

@keyframes fade {
  from {
    opacity: 0
  }
  to {
    opacity: 1
  }
}

img {
  width: 300px;
}

p {
  text-align: center;
  font-size: 16px !important;
  font-weight: 600;
}

.back {
  display: block;
  width: 100px;
  text-align: center;
  margin: 20px auto;
  padding: 8px 0;
  border: 1px solid #333;
  border-radius: 100px;
  cursor: pointer;
  color: rgb(27, 27, 27);
  font-size: 15px;
}
</style>