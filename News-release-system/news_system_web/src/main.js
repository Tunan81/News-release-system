import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/gloable.css'
import request from "@/utils/request";
// 富文本编辑器
import  VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import './assets/css/my_style.css'

import Mock from 'mockjs'

require('./mock.js')

Vue.use(VueQuillEditor)

Vue.config.productionTip = false

Vue.use(ElementUI,{size:"small"});

Vue.prototype.request = request

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
