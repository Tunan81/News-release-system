<template>
  <div class='box'>
    <div class='header'>
      <h2>{{ title }}</h2>
      <p>发布日期：{{ releaseTime }} 点击量：[ {{ hits }} ]</p>
    </div>
    <hr/>
    <div v-html='content' class='ql-editor'></div>
    <Backtop />
  </div>

</template>

<script>
import {getnew} from "@/api/api";
import {formatDate} from "@/utils/date";
import Backtop from "@/components/backtop.vue";

export default {
  name: 'News',
  components: {
    Backtop
  },
  data() {
    return {
      content: '',
      hits: '',
      releaseTime: '',
      title: ''
    }
  },
  created() {
    this.getalone()
  },
  methods: {
    getalone() {
      const data = this.$route.query.id
      getnew(data).then(res => {
        console.log(res)
        this.content = res.data.content
        this.hits = res.data.hits
        let date = new Date(res.data.releaseTime)
        this.releaseTime = formatDate(date, 'yyyy-MM-dd hh:mm')
        /* this.releaseTime = new Date(res.data.releaseTime).toLocaleString()*/
        this.title = res.data.title
      })
    }
  }
}
</script>
<style>
.box {
  width: 1200px;
  margin: 0 auto;
}

.header h2 {
  color: rgb(3, 73, 144);
  text-align: center;
}

.header p {
  background-color: rgb(246, 246, 246);
  text-align: center;
  height: 35px;
  line-height: 35px;
}

.ql-editor {
  padding: 12px 0px !important;
}

.ql-editor p {
  letter-spacing: 1px;
  line-height: 25px;
}

.ql-editor img {
  width: 600px;
  height: 400px;
}
</style>