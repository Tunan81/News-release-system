<template>
  <div>
    <!-- 通知公告导航条 -->
    <el-row type='flex' class='row-bg underline marb10' justify='space-between'>
      <span class='color'><b>通知公告</b></span>
      <span @click="goMore('通知公告')" class='liPointer'> 更多<i class='el-icon-d-arrow-right'></i> </span>
    </el-row>
    <!-- 通知公告新闻列表 -->
    <div style='max-height:382px;overflow:hidden;'>
      <ul>
        <li class='lieBiao liPointer' :key='index' v-for='(item, index) in noticeList'>
          <div class='time'>
            {{ item.releaseTime }}
          </div>
          <div class='title' @click='toNoticeMsg(item.id)'>
            {{ item.title }}
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
//import { getMinTitle, getNewsList } from '../api/api'

export default {
  name: 'notice',
  data() {
    return {
      noticeList: []
    }
  },
  created() {
    this.noticeList = [
      {
        title: "学校召开2023届本科生毕业典礼",
        releaseTime: "2023-05-18"
      },
      {
        title: "2023年春季学期期末考试安排公告",
        releaseTime: "2023-05-16"
      },
      {
        title: "学校荣获2019-2020年度优秀研究生导师奖",
        releaseTime: "2023-05-15"
      },
      {
        title: "我校教师代表赴德国参加国际会议",
        releaseTime: "2023-05-12"
      },
      {
        title: "学校举行首届“创意周”活动",
        releaseTime: "2023-05-10"
      },
      {
        title: "学校引进教授博士后工作站启动",
        releaseTime: "2023-05-08"
      },
      {
        title: "我校图书馆扩建工程竣工验收",
        releaseTime: "2023-05-07"
      },
      {
        title: "学校设立新的校区",
        releaseTime: "2023-05-02"
      },
      {
        title: "2023年五一假期放假通知",
        releaseTime: "2023-04-28"
      },
      {
        title: "学校与国内知名企业签订合作协议",
        releaseTime: "2023-04-25"
      }
    ]
  },
  mounted() {
    this.getnews()
  },
  methods: {
    //获取新闻列表
    getnews() {
      const data = {
        current: 1,
        newsCategoryId: 13,
        size: 6
      }
    }
      // getNewsList(data)
      //   .then(res => {
      //     console.log(res)
      //     if (res.code == 200) {
      //       this.noticeList = res.data.records
      //     }
      //   })
      //   .catch(error => {
      //     console.log(error)
      //   })
/*      axios.get("https://mockapi.eolinker.com/6cw39F8a4f8b1d77e98fa5cf3c3fd55dd5dffd07078a7e0/news")
          .then(res => {
            if (res.status == 200) {
              this.noticeList = res.data.list
            }
          })
          .catch(error => {
            console.log(error)
          })
    },*/
/*    // 去到新闻展示页
    toNoticeMsg(id) {
      this.$router.push({
        path: '/home/news',
        query: {
          id: id
        }
      })
    },*/
/*    //去到更多新闻列表页
    goMore(val) {
      this.$router.push({
        path: '/home/moreMessage',
        query: {
          type: val
        }
      })
    }*/
  }
}
</script>

<style scoped>
.underline {
  border-bottom: 1px solid rgb(1, 72, 153);
}

.lieBiao {
  width: 100%;
  height: 45px;
  background: rgb(241, 241, 241);
  display: flex;
  justify-content: space-between;
  overflow: hidden;
  margin-bottom: 10px;
}

.time {
  width: 100px;
  padding: 15px 0px;
  display: flex;
  flex-direction: column;
  background-color: rgb(1, 72, 153);
  color: white;
  text-align: center;
}

.title {
  width: 500px;
  height: 50px;
  overflow: hidden;
  line-height: 50px;
  padding: 0 0 0 10px;
}
</style>

