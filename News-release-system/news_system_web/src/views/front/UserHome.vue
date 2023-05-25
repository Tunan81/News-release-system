<template>
  <el-container class='home-container'>
    <!-- 主页头部 -->
    <el-header>
      <div>
        <img src='../../assets/top_logo.png' alt='' style='height: 20%;width: 80%'/>
      </div>
      <div>
        <img src='../../assets/top_kh.png' alt=''/>
      </div>
    </el-header>
    <!-- 主页导航栏 -->
    <el-row>
      <el-menu mode='horizontal' background-color='rgb(64, 112, 186)' text-color='white' class='menu'
               active-text-color='#000000' :default-active='activeIndex' @select='handleSelect'>
        <el-menu-item :key='index' v-for='(item, index) in menuList' :index='item.id.toString()'>
          {{ item.name }}
        </el-menu-item>
      </el-menu>
    </el-row>
    <el-container>
      <!-- 更变的内容区域 -->
      <el-main class='main'>
        <router-view class='box'></router-view>
      </el-main>
    </el-container>
    <MyFooter></MyFooter>
  </el-container>
</template>

<script>
import MyFooter from "@/components/myFooter.vue";

export default {
  name: "userHome",
  components: {
    MyFooter
  },
  data() {
    return {
      activeIndex: '1',
      menuList: [
        {name: '首页', id: 1},
        {name: '学校概况', id: 2},
        {name: '学风建设', id: 3},
        {name: '重工榜样', id: 4},
        {name: '学科竞赛', id: 5},
        {name: '党史学习', id: 6},
        {name: '公共服务', id: 7},
        {name: '学校首页', id: 8},
        {name: '联系我们', id: 9}
      ],
    }
  },
  methods: {
    formatterTitle(t) {
      switch (t) {
        case '1':
          return 'Index'
        case '2':
          return 'Introduce'
        case '3':
          return 'Education'
        case '4':
          return 'Star'
        case '5':
          return 'Competition'
        case '6':
          return 'History'
        case '7':
          return 'Services'
        case '8':
          return 'SchoolHome'
        case '9':
          return 'Info'
      }
    },
    handleSelect(key, keyPath) {
      if (key == '8') {
        window.open('http://www.cqie.edu.cn/')
      } else {
        this.$router.push({
          path: '/userHome/' + this.formatterTitle(key),
          query: {
            title: this.formatterTitle(key),
            id: key
          }
        })
      }
    }
  },
}
</script>

<style scoped>
.home-container {
  height: 100%;
}

.el-header {
  background-image: url("@/assets/top_bk1.jpg");
}

.home-container {
  min-height: 100vh;
  height: 100%;
}

.el-header {
  height: 120px !important;
  padding: 0 80px 0 120px;
  overflow: hidden;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-menu {
  .el-menu-item {
    width: 150px;
    font-size: 15px;
    text-align: center;
  }
}

.menu {
  display: flex;
  justify-content: space-between;
}

.main {
  padding: 20px 5px;
}

.box {
  width: 1200px;
  margin: 0 auto;
}
</style>
