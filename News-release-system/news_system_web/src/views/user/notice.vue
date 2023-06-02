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
import {getNewsList} from "@/api/api";
import {formatDate} from "@/utils/date";

export default {
  name: 'notice',
  data() {
    return {
      noticeList: []
    }
  },
  created() {
    this.getnews()
  },
  mounted() {
    //this.getnews()
  },
  methods: {
    //获取新闻列表
    getnews() {
      const data = {
        current: 1,
        newsCategoryId: 1,
        size: 7
      }
      getNewsList(data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.noticeList = res.data.records
              //格式化时间
              this.noticeList.forEach(item => {
                let date = new Date(item.releaseTime)
                item.releaseTime = formatDate(date, 'yyyy-MM-dd')
              })
            }
          })
          .catch(error => {
            console.log(error)
          })
    },
    toNoticeMsg(id) {
      this.$router.push({
        path: '/userHome/news',
        query: {
          id: id
        }
      })
    },
    //去到更多新闻列表页
    goMore(val) {
      this.$router.push({
        path: '/userHome/moreMessage',
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

