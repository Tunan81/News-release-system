<template>
  <div style="margin-left: 30px">
    <div>
      <el-row class="underline marb5">
        <span class="color"><b>友情链接</b></span>
      </el-row>
      <div>
        <div class="aLianJ marb10">
          <a href="http://rj.cqie.edu.cn/" class="anone" target="_block">软件与人工智能学院</a>
        </div>
        <div class="aLianJ marb10">
          <a href="http://zdh.cqie.edu.cn/" class="anone" target="_block">计算机与物联网学院</a>
        </div>
        <div class="aLianJ marb10">
          <a href="http://dz.cqie.edu.cn/" class="anone" target="_block">电子信息学院</a>
        </div>
        <div class="aLianJ marb10">
          <a href="http://tm.cqie.edu.cn/" class="anone" target="_block">建筑工程学院</a>
        </div>
        <div class="aLianJ marb10">
          <a href="http://dh.cqie.edu.cn/" class="anone" target="_block">数字艺术学院</a>
        </div>
        <div class="aLianJ marb10">
          <a href="http://gl.cqie.edu.cn/" class="anone" target="_block">管理学院</a>
        </div>
        <div class="aLianJ marb10">
          <a href="http://ts.cqie.edu.cn/" class="anone" target="_block">通识学院</a>
        </div>
        <div class="aLianJ marb10">
          <a @click="$router.push('/login')" class="anone" target="_block" style="cursor: pointer">重工新闻发布系统后台</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'other',
  data() {
    return {}
  }
}
</script>
<style scoped>
.underline {
  border-bottom: 1px solid rgb(1, 72, 153);
}

a {
  color: #0f99e9;
}

.aLianJ {
  height: 30px;
  background-color: rgb(242, 243, 244);
  text-align: center;
  line-height: 30px;
}

.color {
  color: rgb(1, 72, 153);
}
</style>
