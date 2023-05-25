<template>
  <div class="form-container">
    <h2 class="form-title">发布新闻</h2>
    <form class="form" @submit.prevent="onSubmit">
      <div class="form-item">
        <label for="title" class="form-label">标题:</label>
        <el-input v-model="title" placeholder="请输入标题" style="width: 210px"></el-input>
      </div>
      <div class="form-item">
        <label for="value" class="form-label">类型:</label>
        <div class="block">
          <el-cascader
              v-model="value"
              :options="options"
              :props="{ expandTrigger: 'hover' }"
              @change="handleChange"></el-cascader>
        </div>
      </div>
      <div>
        <label for="content" class="form-label">内容:</label>
      </div>
        <quill-editor class="form-input"
                      v-model="content"
                      ref="myQuillEditor"
                      :height="1000"
                      :options="editorOption"
                      @blur="onEditorBlur()"
                      @focus="onEditorFocus()"
                      @change="onEditorChange($event)"
        >
        </quill-editor>
<!--      <div class="form-input">
        <MyQuillEditor v-model="Content"></MyQuillEditor>
      </div>-->
      <div class="form-item">
        <el-button type="primary" native-type="submit">发布</el-button>
      </div>
    </form>
  </div>
</template>
<script>
//import MyQuillEditor from "@/components/myQuillEditor.vue";
// 工具栏配置
import {forEach} from "core-js/stable/dom-collections";

const toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
  ['blockquote', 'code-block'], // 引用  代码块
  [{ header: 1 }, { header: 2 }], // 1、2 级标题
  [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
  [{ script: 'sub' }, { script: 'super' }], // 上标/下标
  [{ indent: '-1' }, { indent: '+1' }], // 缩进
  [{'direction': 'rtl'}], // 文本方向
  [{ size: ['small', false, 'large', 'huge'] }], // 字体大小
  [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
  [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
  [{ font: [] }], // 字体种类
  [{ align: [] }], // 对齐方式
  ['clean'], // 清除文本格式
  ['link', 'image','video'] // 链接、图片、视频 ['link', 'image', 'video']
]
export default {
  name: "addNews",
/*  components: {
    MyQuillEditor
  },*/
  data() {
    return {
      value: [],
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
      content: '',
      quillUpdateImg: false,
      editorOption: {
        theme: 'snow',
        placeholder: '请在这里输入...',
        modules: {
          toolbar: {
            container: toolbarOptions,
            handlers: {
              // 处理图片上传
            }
          },
          // 拖拽上传和调整图片大小
          imageDrop: true,
          imageResize: {
            displayStyles: {
              backgroundColor: 'black',
              border: 'none',
              color: 'white'
            },
            modules: ['Resize', 'DisplaySize', 'Toolbar']
          },
        },
      },
      type: "",
    };
  },
  methods: {
    onSubmit() {
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
    },
    // 失去焦点事件
    onEditorBlur() {
    },
    // 获得焦点事件
    onEditorFocus() {
    },
    // 准备富文本编辑器
    onEditorChange(event) {
    },
    handleChange(value) {
      console.log(value);
    },
  },
};
</script>

<style scoped>
.form-container {
  width: 100%;
  max-width: 90%;
  margin: auto;
}

.form-title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}
.form-input{
  display: inline-block;
  height: 500px;
  width: 100%;
}
.form {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}

.form-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.form-label {
  font-size: 16px;
  margin-right: 10px;
}

.editor-container {
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.btn {
  display: inline-block;
  padding: 8px 16px;
  font-size: 16px;
  font-weight: bold;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

.btn:hover {
  background-color: #0069d9;
}


</style>
