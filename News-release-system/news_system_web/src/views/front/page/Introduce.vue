<template>
  <div style="height: 50vh">
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
      <!-- 新闻内容 news-->
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
import globalTitle from '../GlobalTitle.vue'
//import { getMinTitle, getNewsList, getnew } from '../../api/api'

export default {
  name: 'Introduce',
  components: {
    globalTitle
  },
  data() {
    return {
      pageSize: 5,
      currentPage: 1,
      total: 0,
      item: {},
      cont: '',
      menuList: [],
      newsList: [],
      new: {} //新闻对象
    }
  },
  created() {
  },
  mounted() {
    //this.getTitle()
    this.menuList = [
      {
        id: 1,
        name: '学院简介'
      },
      {
        id: 2,
        name: '校长寄语'
      },
      {
        id: 3,
        name: '荣誉校长'
      },
      {
        id: 4,
        name: '现任领导'
      },
      {
        id: 5,
        name: '重庆工程学院章程'
      },
      {
        id: 6,
        name: '学校风光'
      }
    ]
  },
  watch: {
    menuList(n, o) {
      this.cont = this.menuList[0].name
      this.getnews(this.menuList[0]) //改变对象，菜单栏的东西要写到监听属性里边
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
    show(item) {
      // console.log(item.id);
      this.$router.push({path: '/home/news', query: {id: item.id}})
    }
    /*  //获取小标题的id
      getTitle() {
        const data = Number(this.$route.query.id)
        getMinTitle(data)
          .then(res => {
            // console.log(res);
            if (res.code == 200) {
              this.menuList = res.data
            }
          })
          .catch(err => {
            console.log(err)
          })
      },
      //小标题菜单的球切换
      changeMenu(val) {
        this.cont = val.name
        this.getnews(val)
      },
      //获取到了新闻列表，里边只有一个对象
      getnews(item) {
        const data = {
          current: 1,
          newsCategoryId: item.id,
          size: 5
        }
        getNewsList(data)
          .then(res => {
            // console.log(res);
            if (res.code == 200) {
              this.newsList = res.data.records
              this.getalone(this.newsList[0].id)
            }
          })
          .catch(error => {
            console.log(error)
          })
      },
      //获取新闻内容,得到一个新闻对象
      getalone(id) {
        const data = id
        getnew(data)
          .then(res => {
            // console.log(res);
            if (res.code == 200) {
              this.new = res.data
              console.log(this.new.content)
            }
          })
          .catch(error => {
            console.log(error)
          })
      }*/
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

.ql-editor {
  padding: 12px 0px !important;
}
</style>
