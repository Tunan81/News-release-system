<template>
  <div style="margin-left: 25vh;">
    <!-- 首页上半部分 -->
    <el-row type='flex' justify='space-between'>
      <div class='block marr10' style="padding:0px 20px 5px 0px;">
<!--        <el-carousel height='400px' arrow='always' :interval='3000'>
&lt;!&ndash;          <el-carousel-item v-for='(item, index) in imgList' :key='index'>
            <div @click='gonew(item.id)' class='cursor' style='width:590px; height:450px'>
              <img :src="item.picturePath" alt='' style='width:100%; height:90%' />
            </div>
          </el-carousel-item>&ndash;&gt;
          <el-carousel-item>
            <div class='cursor'>
              <img src="~@/assets/lb_01.jpg" style='width:100%; height:90%'>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <div class='cursor'>
              <img src="~@/assets/lb_02.jpg" style='width:100%; height:90%'>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <div class='cursor'>
              <img src="~@/assets/lb_03.jpg" style='width:100%; height:90%'>
            </div>
          </el-carousel-item>
        </el-carousel>-->
        <el-carousel height='410px' arrow='always' :interval='3000'>
          <el-carousel-item>
            <div class='cursor' style='width:100%; height:90%'>
              <img src="~@/assets/lb_01.jpg" style='width:100%; height:100%'>
              <div class='title'>喜报！学校荣获“重庆五一劳动..</div>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <div class='cursor' style='width:100%; height:90%'>
              <img src="~@/assets/lb_02.jpg" style='width:100%; height:100%'>
              <div class='title'>国科大第二届卓越工程师班举行..</div>
            </div>
          </el-carousel-item>
          <el-carousel-item>
            <div class='cursor' style='width:100%; height:90%'>
              <img src="~@/assets/lb_03.jpg" style='width:100%; height:100%'>
              <div class='title'>学校召开学习贯彻习近平新时代..</div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
      <notice class='notice' />
    </el-row>
    <!-- 首页下半部分 -->
    <el-row type='flex' class='row-bg' justify='space-between'>
      <work class='work' />
      <school class='school' />
      <other class='other' />
    </el-row>

    <!-- 定位fixed -->
    <div class='fixed1'>
      <a @click="$router.push('Info')"><img src='~@/assets/front/phone.jpg' alt='' /></a>
    </div>
    <div class='fixed2'>
      <a @click="$router.push('/MapView')"><img src='~@/assets/front/map.jpg' alt='' /></a>
    </div>
  </div>
</template>
<script>
import notice from '../notice.vue'
import work from '../work.vue'
import school from '../school.vue'
import other from '../other.vue'

//import { getNewsList } from '../../api/api'

export default {
  components:{notice ,work, school ,other},
  name: 'index',
  data() {
    return {
      imgList: []
    }
  },
  created() {
    //this.getnews()
  },
  methods: {
/*    getnews() {
      const data = {
        current: 1,
        newsCategoryId: 16,
        size: 3
      }
      getNewsList(data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.imgList = res.data.records
            }
          })
          .catch(err => {
            console.log(err)
          })
    },*/
/*    gonew(id) {
      this.$router.push({ path: '/home/news', query: { id: id } })
    }*/
  }
}
</script>
<style scoped>
.block {
  width: 60%;
}
.title {
  position: absolute;
  bottom: 10px;
  left: 0;
  width: 100%;
  padding: 10px;
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
