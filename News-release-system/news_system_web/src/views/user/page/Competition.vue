<template>
  <div>
    <el-row>
      <globalTitle/>
    </el-row>
    <el-row class='mart10'>
      <!-- 小标题 -->
      <el-col :span='4' class='marr10'>
        <ul>
          <li v-for='(item, index) in menuList' :key='index' class='liStylenone liPointer marb10'
              @click='changeMenu(item)'>
            <b :class="cont == item.name ? 'ft-blue' : 'ft-black'">{{ item.name }}</b>
          </li>
        </ul>
      </el-col>
      <!-- 新闻内容 -->
      <el-card class='box-card'>
        <p class='name'>{{ this.cont }}</p>
        <hr class='namehr'/>
        <el-col :span='24'>
          <div>
            <el-row class='marb10' :key='index' v-for='(item, index) in newsList'>
              <div class='flex-v flex-between marb5 cursor' @click='show(item)'>
                <div>{{ item.title }}</div>
                <div>{{ item.releaseTime }}</div>
              </div>
              <el-divider class='hr'></el-divider>
            </el-row>
          </div>
          <!-- 分页 -->
          <div class='fenye'>
            <el-pagination background @current-change='handleCurrentChange' :current-page.sync='currentPage'
                           :page-size='pageSize' layout='prev, pager, next,total' :total='total'></el-pagination>
          </div>
        </el-col>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import globalTitle from '../components/GlobalTitle.vue'
//import { getMinTitle, getNewsList } from '../../api/api'

export default {
  components: {globalTitle},
  name: 'Competition',
  data() {
    return {
      cont: '',
      menuList: [],
      newsList: [],
      pageSize: 6,
      currentPage: 1,
      total: 0,
      item: {}
    }
  },
  created() {
  },
  mounted() {
    //this.getTitle()
    this.menuList = [
      {
        id: 1,
        name: '竞赛动态'
      },
      {
        id: 2,
        name: '竞赛通知'
      },
      {
        id: 3,
        name: '竞赛成果'
      },
      {
        id: 4,
        name: '竞赛指导'
      }
    ]
  },
  watch: {
    menuList(n, o) {
      this.cont = this.menuList[0].name
      this.item = this.menuList[0]
      this.getnews(this.menuList[0])
    }
  },
  methods: {
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.getnews(this.item)
    },
    getTitle() {
      const data = Number(this.$route.query.id)
      getMinTitle(data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.menuList = res.data
            }
          })
          .catch(err => {
            console.log(err)
          })
    },
    changeMenu(val) {
      this.cont = val.name
      this.item = val
      this.currentPage = 1
      this.getnews(val)
    },
    getnews(item) {
      const data = {
        current: this.currentPage,
        newsCategoryId: item.id,
        size: this.pageSize
      }
      getNewsList(data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.newsList = res.data.records
              this.total = Number(res.data.total)
            }
          })
          .catch(error => {
            console.log(error)
          })
    },
    // 跳转到新闻展示页面
    show(item) {
      // console.log(item.id);
      this.$router.push({path: '/userHome/news', query: {id: item.id}})
    }
  }
}
</script>

<style scoped>
ul {
  width: 200px;
}

li {
  background-color: rgb(242, 243, 245);
  height: 50px;
  line-height: 50px;
  text-align: center;
}


.ft-blue {
  color: rgb(9, 143, 252);
}

.ft-black {
  color: #000;
}

.box-card {
  height: 500px;
  position: relative;

  .fenye {
    position: absolute;
    bottom: 10px;
  }
}
</style>
