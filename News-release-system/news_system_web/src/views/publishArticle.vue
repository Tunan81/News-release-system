<template>
  <div>
    <h1 class="form-title">发布新闻</h1>
    <el-steps :active="activeStep" align-center>
      <el-step title="输入标题和类型"></el-step>
      <el-step title="输入发布时间和上传图片"></el-step>
      <el-step title="编辑内容"></el-step>
    </el-steps>
    <el-form ref="form" v-show="activeStep === 0">
      <div class="form-item">
        <el-label class="title">新闻标题:</el-label>
        <el-input v-model="title" style="width: 150px; margin-left: 10px" placeholder="请输入标题"></el-input>
      </div>
      <div class="form-item">
        <el-label>新闻类型:</el-label>
        <el-cascader
            v-model="value"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange"
            style="width: 150px;margin-left: 10px"
            placeholder="请选择新闻类型"
        ></el-cascader>
      </div>
      <div class="form-item">
        <el-button type="primary" @click="nextStep">下一步</el-button>
      </div>
    </el-form>
    <el-form ref="form" v-show="activeStep === 1">
      <div class="form-item">
        <el-label>发布时间:</el-label>
        <el-date-picker v-model="publishTime" type="datetime" style="width: 200px; margin-left: 20px"></el-date-picker>
      </div>
      <div class="form-item">
        <el-label>上传展示图片:</el-label>
        <el-upload
            class="avatar-uploader"
            action="http://localhost:9090/file/upload/icon"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
        >
          <img v-if="form.coverImage" :src="form.coverImage" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <div class="form-item">
        <el-button @click="prevStep">上一步</el-button>
        <el-button type="primary" @click="nextStep">下一步</el-button>
      </div>
    </el-form>
    <el-form ref="form" v-show="activeStep === 2">
      <div class="form-item">
        <el-label>新闻内容:</el-label>
        <br>
        <br>
        <MyEditor @update="getContent" />
      </div>
      <div class="form-item">
        <el-button @click="prevStep">上一步</el-button>
        <el-button type="info" @click="TemporarySave">暂存</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import MyEditor from "@/components/MyEditor.vue";
export default {
  name: "publishArticle",
  components: {
    MyEditor
  },
  data() {
    return {
      form: {},
      activeStep: 0,
      value: [],
      content: '', // 富文本内容
      options: [{
        value: '首页',
        label: '首页',
        children: [{
          newsCategoryId: 1,
          value: '通知公告',
          label: '通知公告',
        }, {
          newsCategoryId: 2,
          value: '工作动态',
          label: '工作动态',
        }, {
          newsCategoryId: 3,
          value: '学院动态',
          label: '学院动态',
        }],
      }, {
        value: '学院概况',
        label: '学院概况',
        children: [{
          newsCategoryId: 4,
          value: '学院简介',
          label: '学院简介',
        }, {
          newsCategoryId: 5,
          value: '学院领导',
          label: '学院领导',
        }, {
          newsCategoryId: 6,
          value: '组织机构',
          label: '组织机构',
        }, {
          newsCategoryId: 7,
          value: '学院风采',
          label: '学院风采',
        }]
      }, {
        value: '学风建设',
        label: '学风建设',
        children: [{
          newsCategoryId: 8,
          value: '学院建设',
          label: '学院建设',
        }, {
          newsCategoryId: 9,
          value: '学习建设',
          label: '学习建设',
        }, {
          newsCategoryId: 10,
          value: '考风建设',
          label: '考风建设',
        }]
      }, {
        value: '学科竞赛',
        label: '学科竞赛',
      }, {
        value: '公共服务',
        label: '公共服务',
      }],
      title: "",
      newsCategoryIdMap: {
        通知公告: 1,
        工作动态: 2,
        学院动态: 3,
        学院简介: 4,
        学院领导: 5,
        组织机构: 6,
        学院风采: 7,
        学院建设: 8,
        学习建设: 9,
        考风建设: 10,
      },
      type: "",
      publishTime: '',
      uploadedImage: '',
    };
  },
  methods:{
    handleChange(value) {
      console.log(value);
    },
    getContent(data){
      this.content = data;
      console.log(this.content);
    },
    nextStep() {
      if (this.activeStep < 2) {
        this.activeStep++;
      }
    },
    prevStep() {
      if (this.activeStep > 0) {
        this.activeStep--;
      }
    },
    handleAvatarSuccess(res) {
      this.$message.success("上传成功")
      this.form.coverImage = res
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    save(){
      // 获取选中的最后一级子节点的值
      let value = this.value[this.value.length - 1];
      // 获取新闻分类id
      let newsCategoryId = this.newsCategoryIdMap[value];
      // 获取用户id
      let userId = localStorage.getItem("loginUser") ? JSON.parse(localStorage.getItem("loginUser")).userId : ""
      this.request.post("/news", {
        type: value,
        title: this.title,
        content: this.content,
        userId: userId,
        newsCategoryId: newsCategoryId,
        publishTime: this.publishTime,
        coverImage: this.form.coverImage,
        status: 1,
      }).then((res) => {
        if (res.code == "200") {
          this.$message({
            message: "发布成功",
            type: "success",
          });
          this.$router.push("/news");
        } else {
          this.$message({
            message: "发布失败",
            type: "error",
          });
        }
      });
    },
    TemporarySave(){
      let value = this.value[this.value.length - 1];
      let newsCategoryId = this.newsCategoryIdMap[value];
      let userId = localStorage.getItem("loginUser") ? JSON.parse(localStorage.getItem("loginUser")).userId : ""
      this.request.post("/news/temporary", {
        type: value,
        title: this.title,
        content: this.content,
        userId: userId,
        newsCategoryId: newsCategoryId,
        publishTime: this.publishTime,
        coverImage: this.form.coverImage,
        status: 0,
      }).then((res) => {
        if (res.code == "200") {
          this.$message({
            message: "暂存成功",
            type: "success",
          });
          this.$router.push("/news");
        } else {
          this.$message({
            message: "暂存失败",
            type: "error",
          });
        }
      });
    }
  }
}
</script>

<style scoped>
.form-title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}
.form-item {
  margin-bottom: 20px;
}
.el-label {
  display: inline-block;
  width: 100px;
  text-align: right;
  margin-right: 10px;
}
.el-input {
  width: 300px;
}
.el-cascader {
  width: 300px;
}
.upload-demo {
  display: inline-block;
}

.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 500px;
  height: 500px;
  border-radius: 10px;
}
</style>
