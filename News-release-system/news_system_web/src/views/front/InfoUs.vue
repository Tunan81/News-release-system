<template>
  <div style='font-size: 16px'>
  <el-descriptions class='margin-top' title='关于我们'
                   :column='2'
                   :size='size' border
                   style='margin: 30px'>
    <el-descriptions-item>
      <template slot='label'>
        <i class='el-icon-mobile-phone'></i>
        联系方式
      </template>
      2636340500@qq.com
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot='label'>
        <i class='el-icon-tickets'></i>
        技术选型
      </template>
      后端：
      <el-tag class='tag' size='small'>SpringBoot</el-tag>
      <el-tag class='tag' size='small'>MyBatis-Plus</el-tag>
      <br>
      前端：
      <el-tag class='tag' size='small'>Vue</el-tag>
      <el-tag class='tag' size='small'>Echarts</el-tag>
      <el-tag class='tag' size='small'>Element-Ui</el-tag>
      <el-tag class='tag' size='small'>vue-quill-editor</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot='label'>
        <i class='el-icon-date'></i>
        备注
      </template>
      <div style='font-size: 16px'>
        &emsp;&emsp;本系统由夏海珂开发，数据库设计统一按照阿里巴巴数据库开发规范；后端采用SpringBoot+MyBatisPlus框架快速搭建,并封装接口返回数据的统一结构；前端采用Vue+ElementUi来编写
        前端页面，其中还是用到了富文本编辑器等等插件；UI设计风格统一，严格统一色彩；本系统还有很多不足之处，希望大家多多指教，如果有什么问题可以联系我.
      </div>
    </el-descriptions-item>
  </el-descriptions>
  </div>
</template>

<script>
export default {
  name: 'InfoUs'
}
</script>

<style scoped>

.tag {
  margin: 2px;
}

</style>
