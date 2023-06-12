<template>
  <div style="margin-left: 25vh;">
    <!-- 首页上半部分 -->
    <el-row type='flex'>
      <div class='block marr10' style="padding:0px 20px 5px 0px;">
        <el-carousel height='410px' arrow='always' :interval='3000'>
          <el-carousel-item v-for='(item, index) in TopNewsList' :key='index'>
            <div @click='gonew(item.id)' class='cursor' style='width:590px; height:450px'>
              <img :src="item.coverImage" alt='' style='width:100%; height:90%'/>
              <div class='title'>{{ item.title }}</div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
      <notice class='notice'/>
    </el-row>
    <!-- 首页下半部分 -->
    <el-row type='flex' class='row-bg' justify='space-between'>
      <work class='work'/>
      <school class='school'/>
      <other class='other'/>
    </el-row>
    <!-- 定位fixed -->
    <div class='fixed1'>
      <a @click="$router.push('/InfoUs')"><img src='~@/assets/front/phone.jpg' alt=''/></a>
    </div>
    <div class='fixed2'>
      <a @click="$router.push('/MapView')"><img src='~@/assets/front/map.jpg' alt=''/></a>
    </div>
  </div>
</template>
<script>
import notice from '../notice.vue'
import work from '../work.vue'
import school from '../school.vue'
import other from '../other.vue'
import {getTopNewsList} from "@/api/api";

export default {
  components: {notice, work, school, other},
  name: 'index',
  data() {
    return {
      TopNewsList: []
    }
  },
  created() {
    this.getnews()
  },
  methods: {
    gonew(id) {
      this.$router.push({path: '/userhome/news', query: {id: id}})
    },
    getnews() {
      const data = {
        current: 1,
        size: 5
      }
      getTopNewsList(data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.TopNewsList = res.data.records
            }
          })
          .catch(error => {
            console.log(error)
          })
    }
  }
}
</script>
<style scoped>
.block {
  width: 50%;
}

.title {
  position: absolute;
  bottom: 6px;
  left: 0;
  width: 100%;
  height: 12%;
  padding: 0px;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  font-size: 18px;
  text-align: center;
}

.block div {
  img {
    width: 100%;
    height: 100%;
  }
}

.notice {
  width: 50%;
}

.work {
  width: 40%;
  margin-right: 10px;
}

.school {
  width: 40%;
}

.other {
  width: 20%;
  margin-left: 5px;
}

.fixed1 {
  position: fixed;
  top: 270px;
  right: 2px;
}

.fixed2 {
  position: fixed;
  top: 340px;
  right: 2px;
}
</style>
