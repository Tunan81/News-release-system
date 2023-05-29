<template>
  <div id="goTop">
    <div class="content" v-show="goTopShow" @click="goTop">
      <!-- 自定义您需要的样式 -->
      <img src="../assets/icon/BackTop.png" style="width: 50px"/>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      scrollTop: "",
      goTopShow: false
    };
  },
  watch: {
    scrollTop() {
      if (this.scrollTop > 500) {
        this.goTopShow = true;
      } else {
        this.goTopShow = false;
      }
    }
  },
  methods: {
    handleScroll() {
      this.scrollTop =
          window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop;
      if (this.scrollTop > 500) {
        this.goTopShow = true;
      }
    },
    goTop() {
      let timer = null,
          _that = this;
      cancelAnimationFrame(timer);
      timer = requestAnimationFrame(function fn() {
        if (_that.scrollTop > 0) {
          // 这里可以调滚动速度(越大越快,反之亦然)
          // _that.scrollTop -= 100;//快速
          _that.scrollTop -= 50;
          document.body.scrollTop = document.documentElement.scrollTop =
              _that.scrollTop;
          timer = requestAnimationFrame(fn);
        } else {
          cancelAnimationFrame(timer);
          _that.goTopShow = false;
        }
      });
    }
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  destroyed() {
    window.removeEventListener("scroll", this.handleScroll);
  }
};
</script>

<style scoped>
.content {
  /* 这块自定义位置、大小、样式等 */
  position: fixed;
  right: 40px;
  bottom: 60px;
  width: 40px;
  height: 40px;
  line-height: 40px;
  border-radius: 50%;
  text-align: center;
  padding: 10px;
  /*box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);*/
  z-index: 100;/* 如出现被遮挡请调高它 */
}
</style>
