<template>
  <div class="upload-container">
    <div class="upload">
      <el-select v-model="selectedSource" placeholder="请选择存储源" size="small">
        <el-option v-for="(source, index) in storageSources" :key="index" :label="source.name"
                   :value="source.id"></el-option>
      </el-select>
      <el-upload
          class="upload-demo"
          :action="baseURL + getUploadUrl()"
          :on-success="handleSuccess"
          :before-upload="beforeUpload"
          :file-list="fileList"
      >
        <el-button size="medium" type="primary" style="margin-top: 20px">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">文件大小不超过100MB</div>
      </el-upload>
    </div>
  </div>
</template>

<script>
export default {
  name: "upload",
  data() {
    return {
      selectedSource: '',
      storageSources: [
        {id: 'local', name: '本地存储'},
        {id: 'cos', name: '腾讯云'}
      ],
      fileList: [],
      baseURL: 'http://localhost:9090'
    }
  },
  methods: {
    // 上传前的回调
    beforeUpload(file) {
      const isLt500K = file.size < 100
      if (!isLt500K) {
        this.$message.error('上传文件大小不能超过 100000MB!')
      }
      // 只有选择了存储源，才能发送上传请求
      return this.selectedSource !== ''
    },
    // 上传成功后的回调
    handleSuccess(response, file, fileList) {
      this.$message.success('上传成功!')
    },
    getUploadUrl() {
      // 根据选择的存储源，返回不同的上传接口地址
      switch (this.selectedSource) {
        case 'local':
          return '/file/upload/local'
        case 'cos':
          return '/file/upload/cos'
        default:
          this.$message.error('请选择存储源!')
          return ''
      }
    }
  }
}
</script>

<style scoped>
.upload-container {
  margin-left: 20px;
  margin-top: 20px;
}

.upload {
  width: 300px;
}

.el-upload__input{
  display: none;
}

.el-button--primary,
.el-button--primary:hover {
  background-color: #409EFF;
  border-color: #409EFF;
}

.el-upload__tip {
  color: #999;
  font-size: 12px;
  margin-top: 5px;
}

.el-progress-bar__outer {
  height: 8px;
  border-radius: 4px;
  background-color: #E9F4FE;
}

.el-progress-bar__inner {
  height: 8px;
  border-radius: 4px;
  background-color: #409EFF;
}

</style>