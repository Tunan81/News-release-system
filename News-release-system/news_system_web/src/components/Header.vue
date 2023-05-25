<template>
  <div style="line-height: 60px;display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/home'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ $route.name }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-dropdown style="width: 110px; cursor: pointer">
      <div style="display: inline-block">
        <img :src="loginUser.avatar" alt=""
             style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 10px;">
        <span>{{ loginUser.username }}</span><i class="el-icon-arrow-down" style="margin-left: 0px"></i>
      </div>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <router-link to="/person" style="text-decoration: none;color: #b0cfe9">个人信息</router-link>
        </el-dropdown-item>
        <el-dropdown-item>
          <span to="/login" style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    loginUser: Object
  },
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;
    }
  },
  data() {
    return {}
  },
  methods: {
    collapse() {
      this.$emit("asideCollapse")
    },
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("loginUser")
      this.$message.success("退出成功")
    }
  }
}
</script>

<style scoped>

</style>