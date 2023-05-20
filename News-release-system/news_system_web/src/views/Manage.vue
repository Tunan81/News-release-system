<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'"
              style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" style="padding-bottom: 20px"/>
    </el-aside>
    <el-container>
      <el-header style=";border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" :loginUser="loginUser"/>
      </el-header>
      <el-main>
        <!-- 表示当前页面的子路由会在rounter-view里面展示-->
        <router-view @refreshuUser="getUser"/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";


export default {
  name: 'Home',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      /*pathName: String*/
      loginUser: {}
    }
  },
  components: {
    Aside,
    Header
  },
  created() {
    this.getUser()
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    getUser() {
      let username = localStorage.getItem("loginUser") ? JSON.parse(localStorage.getItem("loginUser")).username : ""
      //从后台获取User数据
      this.request.get("/user/username/" + username).then(res => {
        //重新赋值后台最新的user数据
        this.loginUser = res.data
      })
    },
  }
}
</script>

