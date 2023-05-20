<template>
  <div style="height: 50vh;margin-left: 35vh">
    <el-table
      :data='tableData'>
      <el-table-column prop='id' label='编号'></el-table-column>
      <el-table-column prop='xueno' label='学号'></el-table-column>
      <el-table-column prop='name' label='姓名'></el-table-column>
      <el-table-column prop='starname' label='荣誉'></el-table-column>
      <el-table-column prop='example' label='事例'></el-table-column>
      <el-table-column
        prop='opera'
        label='操作'>
        <template v-slot='scope'>
          <a style='color: #0f99e9;cursor: pointer' @click="$router.push('StarDetail?id='+scope.row.id)">详&emsp;情</a>
        </template>
      </el-table-column>
    </el-table>
    <!--  分页-->
    <div>
      <el-pagination
        background
        layout='prev, pager, next'
        :current-page='this.currentpage'
        :page-size='this.pagesize'
        :total=this.total
        @current-change='CurrentChange'
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import globalTitle from '../GlobalTitle.vue'
//import request from '@/utils/request'

export default {
  components: { globalTitle },
  name: 'Star',
  data() {
    return {
      tableData: [],
      currentpage: 1,
      pagesize: 6,
      total: 0
    }
  },
  created() {
    //this.load()
    this.tableData = [
      {
        id: 1,
        xueno: '219970149',
        name: '夏海珂',
        starname: '优秀学生',
        example: '2023年度优秀学生'
      },
      {
        id: 2,
        xueno: '219970103',
        name: '杜艺',
        starname: '优秀学生',
        example: '2023年度优秀学生'
      },
      {
        id: 3,
        xueno: '219970126',
        name: '沈琪闲',
        starname: '优秀学生',
        example: '2023年度优秀学生'
      },
      {
        id: 4,
        xueno: '2018110104',
        name: '赵六',
        starname: '优秀学生',
        example: '2018年度优秀学生'
      },
      {
        id: 5,
        xueno: '2018110105',
        name: '孙七',
        starname: '优秀学生',
        example: '2018年度优秀学生'
      },
      {
        id: 6,
        xueno: '2018110106',
        name: '周八',
        starname: '优秀学生',
        example: '2018年度优秀学生'
      }
    ]
  },
  methods: {
    //加载数据
    load() {
      //发送请求
      request.get('/XdStar/querypage?currentpage=' + this.currentpage + '&pagesize=' + this.pagesize
      ).then(res => {
        if (res.code === 200) {
          console.log(res.data)
          this.tableData = res.data.xdstarList
          this.total = res.data.datalength
        }
      })
    },
    CurrentChange(pageNum) {
      this.currentpage = pageNum
      this.load()
    }
    /*  //加载数据
      load() {
        //发送请求
        request.get('/XdStar/all'
        ).then(res => {
          if (res.code === 200) {
            this.tableData = res.data
          }

        })
      },*/
  }
}
</script>

<style scoped>
h4 {
  text-align: center;
  margin-top: 0;
}

.mart10 {
  margin-top: 10px;
}
</style>
