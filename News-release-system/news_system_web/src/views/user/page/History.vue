<template>
  <div>
    <globalTitle />

    <el-row>
      <el-col :span='12' class='lightgreen-box'>
        <el-card class='box-card'>
          <div slot='header' class='clearfix'>
            <span>重要论述</span>
            <el-button style='float: right; padding: 3px 0' type='text'
                       onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">更多>>
            </el-button>
          </div>
          <div :key='o' class='text item'>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0112/c436975-32329315.html')">继续把党史总结学习教育宣传引向深入</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2021/1225/c436975-32316926.html')">不断巩固拓展党史学习教育成果</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0104/c436975-32323627.html')">以史为鉴、开创未来　埋头苦干、勇毅前行</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2021/0902/c436975-32215502.html')">努力成为可堪大用能担重任的栋梁之才</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span='12' class='orange-box'>
        <el-card class='box-card'>
          <div slot='header' class='clearfix'>
            <span>要闻要论</span>
            <el-button style='float: right; padding: 3px 0' type='text'
                       onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">更多>>
            </el-button>
          </div>
          <div class='text item'>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0402/c436975-32390498.html')">坚持高质量办理人大代表建议 努力做到民有所呼我有所应</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0322/c436975-32380526.html')">关于推动党史学习教育常态化长效化的意见</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2021/1216/c436975-32309607.html')">中央宣讲团到团中央机关宣讲党的十九届六中全会精神</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0402/c436975-32390571.html')">春雨润清明 长忆英雄魂</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span='12' class='lightgreen-box'>
        <el-card class='box-card'>
          <div slot='header' class='clearfix'>
            <span>动态进展</span>
            <el-button style='float: right; padding: 3px 0' type='text'
                       onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">更多>>
            </el-button>
          </div>
          <div class='text item'>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0317/c436975-32377066.html')">以协商聚共识 以共识固团结</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0228/c436975-32361546.html')">破解雷锋精神永恒的密码</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0224/c436975-32358716.html')">用艺术语言讲述不懈奋斗历史征程</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0221/c436975-32356330.html')">陕西省政府领导班子召开党史学习教育专题民主生活会</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span='12' class='orange-box'>
        <el-card class='box-card'>
          <div slot='header' class='clearfix'>
            <span>媒体集萃</span>
            <el-button style='float: right; padding: 3px 0'
                       onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">更多>>
            </el-button>
          </div>
          <div class='text item'>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/437145/441479/index.html')">习近平讲述的故事</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/437127/index.html')">党史百年</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/436979/index.html')">理论·经验</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/436982/index.html')">体会感悟</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span='12' class='lightgreen-box'>
        <el-card class='box-card'>
          <div slot='header' class='clearfix'>
            <span>为民办事</span>
            <el-button style='float: right; padding: 3px 0' type='text'
                       onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">更多>>
            </el-button>
          </div>
          <div class='text item'>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0402/c436975-32390559.html')">官兵急难愁盼 机关紧抓快办</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/440990/440994/index.html')">我为群众办实事 经验做法征集</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0402/c436975-32390559.html')">七一勋章获得者：草鞋书记周永开</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/n1/2022/0218/c436975-32354850.html')">件件有落实 情暖职工心</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span='12' class='orange-box'>
        <el-card class='box-card'>
          <div slot='header' class='clearfix'>
            <span>党史资料</span>
            <el-button style='float: right; padding: 3px 0' type='text'
                       onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">更多>>
            </el-button>
          </div>
          <div class='text item'>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangjian.people.com.cn/GB/436844/index.html')">党的基本知识</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/index.html#baike')">党史百科</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('https://www.dswxyjy.org.cn/GB/427202/')">百年影像</span>
            <br>
            <span style='display:block;height:19px;'
                  onclick="javascript:window.open('http://dangshi.people.com.cn/GB/437104/index.html')">红色展馆</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import globalTitle from '../components/GlobalTitle.vue'

export default {
  components: { globalTitle },
  name: 'History',
  data() {
    return {
      currentDate: new Date()
    }
  },
  methods: {}
}
</script>

<style scoped>

span:hover {
  cursor: pointer;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 590px;
  margin: 10px;
}
</style>
