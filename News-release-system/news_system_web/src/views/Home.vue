<template>
  <div>
    <el-row :gutter='10' style='margin-bottom: 60px'>
      <el-col :span='6'>
        <el-card style='color: #409EFF'>
          <div><i class='el-icon-user'></i>&emsp;用户总数</div>
          <div style='padding: 10px 0;text-align: center; font-weight: bold'>
            {{usernums}}
          </div>
        </el-card>
      </el-col>
      <el-col :span='6'>
        <el-card style='color: #67C23A'>
          <div><i class='el-icon-s-data'></i>&emsp;新闻总数</div>
          <div style='padding: 10px 0;text-align: center; font-weight: bold'>
            {{newsnums}}
          </div>
        </el-card>
      </el-col>
      <el-col :span='6'>
        <el-card style='color: #E6A23C'>
          <div><i class='el-icon-postcard'></i>&emsp;游览总数</div>
          <div style='padding: 10px 0;text-align: center; font-weight: bold'>
            {{h1nums}}
          </div>
        </el-card>
      </el-col>
      <el-col :span='6'>
        <el-card style='color: #909399'>
          <div><i class='el-icon-postcard'></i>&emsp;点赞总数</div>
          <div style='padding: 10px 0;text-align: center; font-weight: bold'>
            {{h2nums}}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top: 10vh">
      <el-col :span="12" style="padding-right:10px">
        <div id="line" style="width: 100%;height: 400px"></div>
      </el-col>
      <el-col :span="12" >
        <div id="cake" style="width: 100%;height: 400px"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Mock from 'mockjs'
import * as echarts from "echarts"

const data = {
  usernums: Mock.Random.integer(100, 500),
  newsnums: Mock.Random.integer(500, 1000),
  h1nums: Mock.Random.integer(50, 200),
  h2nums: Mock.Random.integer(200, 500),
  lineData: Array.from({length: 7}, () => ({
    date: Mock.Random.date('yyyy-MM-dd'),
    announce: Mock.Random.integer(10, 50),
    read: Mock.Random.integer(5, 20)
  })),
  cakeData: [
    {name: '学院简介', value: Mock.Random.integer(1, 10)},
    {name: '校长寄语', value: Mock.Random.integer(1, 10)},
    {name: '专业介绍', value: Mock.Random.integer(5, 20)},
    {name: '现任领导', value: Mock.Random.integer(5, 20)},
    {name: '章程', value: Mock.Random.integer(1, 10)},
    {name: '学习建设', value: Mock.Random.integer(1, 10)},
    {name: '考风考纪', value: Mock.Random.integer(1, 10)},
    {name: '竞赛动态', value: Mock.Random.integer(1, 10)},
    {name: '竞赛通知', value: Mock.Random.integer(1, 10)},
    {name: '竞赛成果', value: Mock.Random.integer(5, 20)},
    {name: '竞赛指导', value: Mock.Random.integer(5, 20)},
    {name: '通知公告', value: Mock.Random.integer(20, 50)},
    {name: '工作动态', value: Mock.Random.integer(20, 50)}
  ]
}

const option1 = {
  title: {
    text: '发布与阅读'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['Announce', 'Read']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  toolbox: {
    feature: {
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: data.lineData.map(item => item.date)
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: 'Announce',
      type: 'line',
      stack: 'total',
      data: data.lineData.map(item => item.announce)
    },
    {
      name: 'Read',
      type: 'line',
      stack: 'total',
      data: data.lineData.map(item => item.read)
    }
  ]
}

const option2 = {
  title: {
    text: '内容分类'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b}: {c} ({d}%)'
  },
  legend: {
    top: '10%',
    orient: 'vertical',
    left: 'left',
    data: data.cakeData.map(item => item.name)
  },
  series: [
    {
      name: '访问来源',
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '30',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: data.cakeData
    }
  ]
}

export default {
  data() {
    return {
      usernums: data.usernums,
      newsnums: data.newsnums,
      h1nums: data.h1nums,
      h2nums: data.h2nums
    }
  },
  mounted() {
    this.drawLineChart()
    this.drawCakeChart()
  },
  methods: {
    drawLineChart() {
      const lineChart = echarts.init(document.getElementById('line'))
      lineChart.setOption(option1)
    },
    drawCakeChart() {
      const cakeChart = echarts.init(document.getElementById('cake'))
      cakeChart.setOption(option2)
    }
  }
}
</script>

<style scoped>
</style>