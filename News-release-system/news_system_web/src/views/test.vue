<template>
  <div>
    <el-row type="flex">
      <div class='block marr10'>
        <el-carousel height='450px' arrow='always' :interval='3000'>
          <el-carousel-item v-for='(item, index) in TopNewsList' :key='index'>
            <div @click='gonew(item.id)' class='cursor' style='width:590px; height:450px'>
              <img :src="item.coverImage" alt='' style='width:100%; height:90%'/>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-row>
  </div>
</template>

<script>
import {getTopNewsList} from "@/api/api";

export default {
  name: "test",
  data() {
    return {
      msg: "test",
      TopNewsList: []
    }
  },
  created() {
    this.getnews()
  },
  methods: {
    test() {
      console.log("test")
    },
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
  bottom: 0px;
  left: 0;
  width: 100%;
  height: 12%;
  padding: 2px;
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