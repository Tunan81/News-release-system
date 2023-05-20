<template>
  <el-container class='home-container'>
    <!-- 主页头部 -->
    <el-header>
      <div>
        <img src='../../assets/top_logo.png' alt='' style='height: 20%;width: 80%' />
      </div>
      <div>
        <img src='../../assets/top_kh.png' alt='' />
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
<!--
    <el-container>
      &lt;!&ndash; 更变的内容区域 &ndash;&gt;
      <el-main class='main' style="display: flex; flex-wrap: wrap;">
        <div class="carousel" style="flex-basis: 45%; max-width: 45%;">
          &lt;!&ndash; 轮播图板块 &ndash;&gt;
          <el-carousel :interval="3000" arrow="always">
            <el-carousel-item>
              <img src="../assets/lb_01.jpg" style="width:100%;height: 100%">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../assets/lb_02.jpg" style="width:100%;height: 100%">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../assets/lb_03.jpg" style="width:100%;height: 100%">
            </el-carousel-item>
          </el-carousel>
        </div>
        <div class="campus-dynamics" style="flex-basis: 45%; max-width: 45%;margin-left: 30px">
          &lt;!&ndash; 校园动态板块 &ndash;&gt;
          <h2>校园动态</h2>
          <ul>
            <li v-for="item in campusDynamics" :key="item.id">
              <a :href="item.link">{{ item.title }}</a>
              <span>{{ item.date }}</span>
            </li>
          </ul>
        </div>
        <div class="media-focus" style="flex-basis: 45%; max-width: 45%;">
          &lt;!&ndash; 媒体聚焦板块 &ndash;&gt;
          <h2>媒体聚焦</h2>
          <ul>
            <li v-for="item in mediaFocus" :key="item.id">
              <a :href="item.link">{{ item.title }}</a>
              <span>{{ item.date }}</span>
            </li>
          </ul>
        </div>
        <div class="notice-announcement" style="flex-basis: 45%; max-width: 45%;margin-left: 30px">
          &lt;!&ndash; 通知公告板块 &ndash;&gt;
          <h2>通知公告</h2>
          <ul>
            <li v-for="item in noticeAnnouncement" :key="item.id">
              <a :href="item.link">{{ item.title }}</a>
              <span>{{ item.date }}</span>
            </li>
          </ul>
        </div>
        <router-view class='box'></router-view>
      </el-main>
    </el-container>-->
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
        { name: '首页', id: 1 },
        { name: '学校概况', id: 2 },
        { name: '学风建设', id: 3 },
        { name: '重工榜样', id: 4 },
        { name: '学科竞赛', id: 5 },
        { name: '党史学习', id: 6 },
        { name: '公共服务', id: 7 },
        { name: '学校首页', id: 8 },
        { name: '联系我们', id: 9 }
      ],
      campusDynamics: [
        {
          id: 1,
          title: "我校荣获省市级一流本科专业建设点",
          date: "2023-05-13",
          link: "#"
        },
        {
          id: 2,
          title: "我校召开学习贯彻党的十九届七中全会精神暨庆祝建党100周年系列活动启动大会",
          date: "2023-05-10",
          link: "#"
        },
        {
          id: 3,
          title: "我校成功举办第三届全国大学生机器人比赛",
          date: "2023-05-08",
          link: "#"
        }
      ],
      mediaFocus: [
        {
          id: 1,
          title: "重庆工程学院软件与人工智能学院：培养高素质软件工程师，引领行业发展",
          date: "2023-05-05",
          link: "#"
        },
        {
          id: 2,
          title: "重庆市教育科研院所工作专家到我校检查指导课题研究",
          date: "2023-04-28",
          link: "#"
        },
        {
          id: 3,
          title: "重庆工程学院设立“信息安全”本科人才培养专业",
          date: "2023-04-20",
          link: "#"
        }
      ],
      noticeAnnouncement: [
        {
          id: 1,
          title: "关于开展“百千万人才工程”第五批次招募工作的通知",
          date: "2023-05-01",
          link: "#"
        },
        {
          id: 2,
          title: "关于开展本科生选修课程评估工作的通知",
          date: "2023-04-25",
          link: "#"
        },
        {
          id: 3,
          title: "关于2023年本科生校外实习工作安排的通知",
          date: "2023-04-15",
          link: "#"
        }
      ]
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
