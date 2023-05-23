<template>
  <div style="margin-left: 0px;height: 300px">
    <el-row type="flex" justify="space-between" class="underline row-bg">
      <span class="color"><b>工作动态</b></span>
      <span @click="goMore('工作状态')" class="liPointer">更多<i class="el-icon-d-arrow-right"></i> </span>
    </el-row>
    <div style="max-height:420px;overflow:hidden;">
      <ul>
        <li :key="index" v-for="(item, index) in workList" class="liPointer">
          <el-card style='margin: 1px; overflow: hidden ' >
          <div @click="toWorkMsg(item.id)" class="">
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
import {getNewsList} from "@/api/api";
export default {
  name: 'work',
  data() {
    return {
      workList: []
    }
  },
  created() {
    this.getnews()
    //this.workList = []
      /*{ id: 1, title: "重庆工程学院2023年本科生招生工作圆满结束" },
      { id: 2, title: "重庆工程学院医学工程系获批硕士学位授权点" },
      { id: 3, title: "重庆市教委来校验收临港校区环境卫生工作" },
      { id: 4, title: "重庆工程学院2023年春季运动会圆满落幕" },
      { id: 21, title: "研究生院召开2023年招生宣传工作部署会议" },
    ]*/
  },
  methods: {
    //获取新闻列表
    getnews() {
      const data = {
        current: 1,
        newsCategoryId: 2,
        size: 5
      }
      getNewsList(data)
        .then(res => {
          console.log(res)
          if (res.code == 200) {
            this.workList = res.data.records
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    toWorkMsg(id) {
      this.$router.push({
        path: '/userHome/news',
        query: {
          id: id
        }
      })
    },
    goMore(val) {
      this.$router.push({
        path: 'moreMessage',
        query: {
          type: val
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
