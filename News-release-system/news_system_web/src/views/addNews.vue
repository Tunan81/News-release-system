<template>
  <div class="news-form">
    <form class="form" @submit.prevent="onSubmit">
      <label for="title" class="form-label">标题:</label>
      <el-input v-model="title" placeholder="请输入标题" style="width: 210px"></el-input>

      <label for="title" class="form-label">类型:</label>
      <el-select v-model="type" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <label for="content" class="form-label">内容:</label>
      <quill-editor class="form-input"
                    v-model="content"
                    ref="myQuillEditor"
                    :options="editorOption"
                    @blur="onEditorBlur()"
                    @focus="onEditorFocus()"
                    @change="onEditorChange($event)">
      </quill-editor>
      <br>
      <br>
      <br>
      <el-button type="primary" native-type="submit">发布</el-button>
    </form>
  </div>
</template>

<script>

export default {
  name: "addNews",
  data() {
    return {
      options: [{
        value: '校园动态',
        label: '校园动态'
      }, {
        value: '学术讲座',
        label: '学术讲座'
      }, {
        value: '媒体聚焦',
        label: '媒体聚焦'
      }, {
        value: '通知公告',
        label: '通知公告'
      }, {
        value: '招生信息',
        label: '招生信息'
      }],
      type: '',
      title: "",
      content: '',
      editorOption: {},
    };
  },
  methods: {
    onSubmit() {
      // 处理提交逻辑，这里可以使用axios等工具发送请求到后端
      this.request.post("/news/addNews", {
        type: this.type,
        title: this.title,
        content: this.content,
        userId: 1,
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
    onEditorBlur() {
    },
    onEditorFocus() {
    },
    onEditorChange(event) {
    },
  },
};
</script>

<style scoped>
.news-form {
  margin: auto;
}

.form-title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.form {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  margin-top: 30px;
}

.form-label {
  display: block;
  font-size: 16px;
  margin-bottom: 5px;
}

.form-input {
  width: 90%;
  height: 400px; /* 修改为更大的高度 */
  font-size: 16px;
}

.form-submit-btn {
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: all 0.2s ease-in-out;
}

.form-submit-btn:hover {
  background-color: #0069d9;
}
</style>
