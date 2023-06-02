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
            <div @click='toSchoolMsg(item.id)' style="width: 200px;text-overflow: ellipsis">
              {{ item.title }}
            </div>
          </el-card>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import {getNewsList} from "@/api/api";

function truncateText(text, maxLength) {
  if (text.length > maxLength) {
    return text.substr(0, maxLength) + '...';
  } else {
    return text;
  }
}

export default {
  name: 'school',
  data() {
    return {
      schoolList: []
    }
  },
  created() {
    this.getnews()
  },
  methods: {
    getnews() {
      const data = {
        current: 1,
        newsCategoryId: 3,
        size: 5
      }
      getNewsList(data)
        .then(res => {
          console.log(res)
          if (res.code == 200) {
            this.schoolList = res.data.records
            this.schoolList.forEach(item => { // 截取标题
              item.title = truncateText(item.title, 20)
            })
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
        path: '/userHome/news',
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
