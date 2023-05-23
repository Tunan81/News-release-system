<template>
  <div class='box'>
<!--    <p>{{ this.$route.query.id }}</p>-->
    <div class='header'>
      <h2>{{ title }}</h2>
      <p>发布日期：{{ releaseTime }} 点击量：[ {{ hits }} ]</p>
    </div>
    <hr/>
    <div v-html='content' class='ql-editor'></div>
  </div>
</template>

<script>
import Mock from 'mockjs'
import {getnew} from "@/api/api";

// 模拟数据
/*Mock.mock('/api/news?id=21', 'get', {
  'data': {
    'hits': 100,
    'releaseTime': '@datetime',
    'title': '重庆工程学院学生在全国大学生智能车竞赛中获佳绩'
  }
})*/
export default {
  name: 'News',
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
    //this.hits = 100
    //this.releaseTime = '2023-05-20 13:15:00'
    //this.title = '研究生院召开2023年招生宣传工作部署会议'
    //this.content = '<p class="ql-align-center">《热风》读后感</p><p class="ql-align-justify">\t起初，我对先生的敬仰源于他那篇激昂慷慨的《热风》：“此后如竟没有炬火，我便是唯一的光。” 如今重新读这篇文章，数次推敲之下，我的心中涌动着对已经成为那“炬火”的先生的崇敬。当代的青年们已摆脱了那种朝气蓬勃却因受冷落而逐渐消逝的境遇。他们勇往直前，不再理会那些压制和打击，追求自己的人生价值，并将对未来的期许化作无限的力量，涌向更高的目标。</p><p class="ql-align-justify">\t我们所习惯的坚持不懈，做事要有恒心的看法，在实践中总是难以坚持。一些名言警句常在耳边回响，但终究是一阵过眼云烟。然而，我们的良知并未被扭曲，反而欣然接受这一份馈赠，让这份“来之不易”的声音，在心间荡起阵阵涟漪。</p><p class="ql-align-justify">\t坚持是必要的，但独善其身从来不值得赞扬。我们常被教导要谦逊、低调，甚至“低调奢华有内涵”和“低调做人”等用语也在网络中广泛流传。但是这种谦虚的势头越来越盛行，最终演变成一种让人不齿的虚伪。我们已经听够了类似“枪打出头鸟”，“木秀于林，风必摧之”之类的说法，但是如果那些飞翔的鸟儿有着足够强健的体魄，那么猎手的枪口就不可能准确瞄准它们；苍翠挺拔的古木如果不向上生长，就不可能获得更多的阳光和雨露；真正强大的枝条也不会因为狂风恐惧而退缩。看到青年们勇往直前，我感到骄傲和高兴。他们端坐学堂，刻苦钻研诗书；奔赴乡村，寒来暑往地扶贫助困；意气风发，青春锋芒毕露。</p><p class="ql-align-justify">\t他们的态度既非桀骜不驯，也非嚣张自大，而是充满“如初春、如朝日、如百卉之萌动、如利刃之新发于硎”的生命力。时至今日，中国青年已经理智成熟，思想更加丰富，他们有自己的故事，不必听取那些自暴自弃者的话语。正如先生所言：“只要不断向上走，尽管只是发出一点点的光和热，也可以像萤火一样，在黑暗中闪耀。并无必须等待炬火的必要。”</p><p class="ql-align-justify">\t先生的一生都专注于文学，他并非为了功名利禄或享乐安逸，而是用尽毕生精力</p><p><br></p>'
  },
  methods: {
    getalone() {
      const data = this.$route.query.id
      getnew(data).then(res => {
        console.log(res)
        this.content = res.data.content
        this.hits = res.data.hits
        this.releaseTime = res.data.releaseTime
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