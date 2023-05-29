<template>
  <div>
    <h1 class="form-title">发布新闻</h1>
    <el-form ref="form">
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
            @change="handleChange" style="width: 150px;margin-left: 10px" placeholder="请选择新闻类型">
        </el-cascader>
      </div>
      <div class="form-item">
        <el-label>新闻内容:</el-label>
        <br>
        <br>
        <MyEditor @update="getContent"/>
      </div>
      <div class="form-item">
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
          value: '学院简介',
          label: '学院简介',
        }, {
          value: '学院领导',
          label: '学院领导',
        }, {
          value: '组织机构',
          label: '组织机构',
        }, {
          value: '学院风采',
          label: '学院风采',
        }]
      }, {
        value: '学风建设',
        label: '学风建设',
        children: [{
          value: '学院建设',
          label: '学院建设',
        }, {
          value: '学习建设',
          label: '学习建设',
        }, {
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
      },
      type: "",
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
</style>