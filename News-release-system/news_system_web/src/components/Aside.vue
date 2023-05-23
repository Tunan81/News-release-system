<template>
  <el-menu :default-openeds="opens" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/logo.png" alt=""
           style="width: 24px; position: relative;top: 5px;margin-right: 5px;border-radius: 50%">
      <b style="color: #cccccc; top: 3px" v-show="logoTextShow">重工校园新闻发布系统</b>
    </div>
    <!--    <el-menu-item index="/">
          <i class="el-icon-house"></i>
          <span slot="title">主页</span>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-collection"></i>
            <span slot="title">新闻管理</span>
          </template>
          <el-menu-item index="/news">新闻列表</el-menu-item>
          <el-menu-item index="/addNews">发布新闻</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-s-shop"></i>
            <span slot="title">新闻类型管理</span>
          </template>
          <el-menu-item index="/newsType">新闻类型列表</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">
            <i class="el-icon-shopping-bag-1"></i>
            <span slot="title">权限管理</span>
          </template>
          <el-menu-item index="/user">用户列表</el-menu-item>
          <el-menu-item index="/role">角色管理</el-menu-item>
          <el-menu-item index="/menu">菜单管理</el-menu-item>
          <el-menu-item index="/file">文件管理</el-menu-item>
        </el-submenu> -->
    <div v-for="item in menus" :key="item.id">
      <div v-if="item.path">
        <el-menu-item :index="item.path">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </div>
      <div v-else>
        <el-submenu :index="item.id + ''">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
          </template>
          <div v-for="subItem in item.children" :key="subItem.id">
            <el-menu-item :index="subItem.path">
              <i :class="subItem.icon"></i>
              <span slot="title">{{ subItem.name }}</span>
            </el-menu-item>
          </div>
        </el-submenu>
      </div>
    </div>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  data() {
    return {
      menus: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) : [],
      opens: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")).map(v => v.id + '') : [],
    }
  }

}
</script>

<style scoped>
.el-menu-item.is-active {
  background-color: rgb(38, 52, 69) !important;
}
.el-menu-item:hover {
  background-color: rgb(38, 52, 69) !important;
}

.el-submenu__title:hover {
  background-color: rgb(38, 52, 69) !important;
}
/*解决收缩菜单文字不消失问题*/
.el-menu--collapse span {
  visibility: hidden;
}
</style>