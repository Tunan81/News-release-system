<template>
  <div class="editor">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editor"
        :defaultConfig="toolbarConfig"
        :mode="mode"
    />
    <Editor
        v-model="html"
        class="content"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="onCreated"
        @onChange="onChange"
    />
  </div>
</template>

<script>
import Vue from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

export default Vue.extend({
  // 工具栏和编辑器是分开的
  components: { Editor, Toolbar },
  data() {
    return {
      editor: null,//富文本实例(后续调用API全靠它)
      html: '',//当前富文本内容(双向绑定)
      toolbarConfig: { },//工具栏配置,详见文档
      editorConfig: {
        placeholder: '开始编辑新闻吧...',
        MENU_CONF: {
          // 配置上传图片
          uploadImage: {
            server: 'http://localhost:9090/file/upload/editor',
            fidldName: 'file', // 上传图片时，接收图片的接口名称，不填默认为 file
            metaWithUrl: true, // 上传图片时，是否使用图片 url 作为数据的 meta，默认为 false
            headers: { Accept: 'text/x-json' },
            maxupload: 1, // 限制一次最多上传 1 张图片
            maxFileSize: 100 * 1024 * 1024, // 100M

            base64LimitSize: 5 * 1024, // 5kb 以下插入 base64
            onBeforeUpload(file) {
              console.log('onBeforeUpload', file)
              return file // 返回哪些文件可以上传
            },
            //
            onProgress(progress) {
              console.log('onProgress', progress)
            },
            onSuccess(file, res) {
              console.log('onSuccess', file, res)
            },
            onFailed(file, res) {
              alert(res.message)
              console.log('onFailed', file, res)
            },
            onError(file, err, res) {
              alert(err.message)
              console.error('onError', file, err, res)
            },
          },
        },
      },//编辑器配置,详见文档
      mode: 'default', // or 'simple'
    }
  },

  mounted() {
    // 模拟 ajax 请求，异步渲染编辑器(通常是编辑富文本时传入过来的默认内容)
    setTimeout(() => {
      this.html = '<p>模拟 Ajax 异步设置内容 HTML</p>'
    }, 1500)
  },

  methods: {
    /**
     * [API] 设置内容
     * @description 调用富文本实例的 setHtml() 方法
     * @return void
     */
    uploaadImg(file, insertFn){
      console.log(file)
      this.$emit('uploadImg', file, insertFn)
    },

    /**
     * [回调函数] 富文本加载完毕
     * @description 创建实例
     * @param {Object} editor - 实例
     * @return void
     */
    onCreated(editor) {
      // 创建富文本实例
      this.editor = Object.seal(editor);
    },

    /**
     * [回调函数] 监听富文本发生变化(编辑器内容、选区变化时)
     * @description 通知父组件更新内容
     * @param {Object} editor - 实例
     */
    onChange(editor) {
      // console.log(editor.getHtml())
      this.$emit('update', editor.getHtml())
      // 父组件接收: <Editor @update="XXX" />
    },

    /**
     * [API] 插入自定义内容
     * @description 调用API示例(后续也是这样使用)
     * @return void
     */
    setHtml() {
      // 获取实例
      const editor = this.editor
      if (editor == null) return
      // 调用API
      editor.dangerouslyInsertHtml(`<h1>标题</h1><p>文本 <b>加粗</b></p>`)
    },

    /**
     * [API] 清空富文本内容
     * @description 调用API示例(后续也是这样使用)
     * @return void
     */
    clear() {
      // 获取实例
      const editor = this.editor
      if (editor == null) return
      // 调用API
      editor.clear()
    },

  },

  beforeDestroy() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy() // 组件销毁时，及时销毁编辑器
  },

})
</script>

<style src="@wangeditor/editor/dist/css/style.css"></style>
<style scoped>
.editor {
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 10px;
}
.content {
  height: 500px;
  overflow-y: hidden;
}
</style>
