<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 150px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid"></i>用户总数</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">
            100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #edd014">
          <div><i class="el-icon-document"></i>文章总数</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">
            520
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #00c317">
          <div><i class="el-icon-view"></i>浏览总数</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">
            500
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #dd2215">
          <div><i class="el-icon-trophy"></i>点赞总数</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">
            200
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>

export default {
  name: "Home",
  data() {
  }
}

</script>

<style scoped>

</style>