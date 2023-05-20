<template>
  <div style="margin-left: 30px">
    <el-row type='flex' justify='space-between' class='underline'>
      <span class='color'><b>学院动态</b></span>
      <span @click="goMore('学院动态')" class='liPointer'>更多<i class='el-icon-d-arrow-right'></i> </span>
    </el-row>
    <div style='max-height:420px;overflow:hidden; margin-right: 1px'>
      <ul>
        <li :key='index' v-for='(item, index) in schoolList' class='liPointer'>
          <el-card style='margin: 1px ;overflow: hidden;word-break:keep-all;white-space:nowrap;'>
            <div @click='toSchoolMsg(item.id)'>
              {{ item.title }}
            </div>
          </el-card>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
//import { getNewsList } from '../api/api'

export default {
  name: 'school',
  data() {
    return {
      schoolList: []
    }
  },
  created() {
    //this.getnews()
    this.schoolList = [
      { id: 1, title: "重庆工程学院计算机科学系教授获得国家自然科学基金" },
      { id: 2, title: "2023年重庆工程学院暑期社会实践活动顺利结束" },
      { id: 3, title: "重庆工程学院千人计划教授在纳米材料研究领域取得新成果" },
      { id: 4, title: "学工部举办“青春心向党·建功新时代”主题演讲比赛" },
      { id: 5, title: "外语系师生参加2023年亚洲大学生英语辩论赛，荣获佳绩" }
    ];

  },
  methods: {
    getnews() {
      const data = {
        current: 1,
        newsCategoryId: 15,
        size: 6
      }
      getNewsList(data)
        .then(res => {
          console.log(res)
          if (res.code == 200) {
            this.schoolList = res.data.records
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    //前往更多信息页面
    goMore(val) {
      this.$router.push({
        path: 'moreMessage',
        query: {
          type: val
        }
      })
    },
    //前往信息展示页面
    toSchoolMsg(id) {
      this.$router.push({
        path: '/home/news',
        query: {
          id: id
        }
      })
    }
  }
}
</script>
<style scoped>
.underline {
  border-bottom: 1px solid rgb(1, 72, 153);
}

ul {
  padding-left: 0;
}

li {
  list-style: none;
  height: 60px;
}

a {
  text-decoration: none;
}

.bgc {
  background: rgb(241, 241, 241);
  height: 55px;
  line-height: 55px;
}
</style>
