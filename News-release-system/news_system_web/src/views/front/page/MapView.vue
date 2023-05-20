<template>
  <div>
    <el-button @click="$router.push('userHome/index')" style='position: absolute;right: 0;z-index: 99;'>返回主页</el-button>
    <el-tabs v-model='activeName' type='card' @tab-click='handleClick'>
      <el-tab-pane label='平面地图' name='first'>
        <div id='container' style='width: 100% ;height: calc(100vh - 60px)'></div>
      </el-tab-pane>
      <el-tab-pane label='3D地图' name='second'>
        <div id='allmap' style='width: 100% ;height: calc(100vh - 60px)'></div>
      </el-tab-pane>
      <el-tab-pane label='我的位置' name='third'>
        <div id='mymap' style='width: 100% ;height: calc(100vh - 60px)'></div>
      </el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>
var content = [
  '<div style=\'font-size: 14px; color: red; width: 200px; height: 70px\'>重庆工程学院是经教育部批准设立的一所以工学为主，' +
  '以软件、电子信息为特色，经济管理和人文艺术等学科专业协调发展的全日制普通本科高校。</div>'
]
export default {
  name: 'MapView',
  data() {
    return {
      activeName: 'third'
    }
  },
  created() {
  },
  mounted() {
    // 我的位置 浏览器
    var mymap = new BMapGL.Map('mymap')
    var point = new BMapGL.Point(106.595219,29.421876)
    mymap.centerAndZoom(point, 12)
    var geolocation = new BMapGL.Geolocation()
    geolocation.getCurrentPosition(function(r) {
      if (this.getStatus() == BMAP_STATUS_SUCCESS) {
        var mk = new BMapGL.Marker(r.point)
        mymap.addOverlay(mk)
        mymap.panTo(r.point)
        alert('您的位置：' + r.point.lng + ',' + r.point.lat)
      } else {
        alert('failed' + this.getStatus())
      }
    })
    var scaleCtrl = new BMapGL.ScaleControl()  // 添加比例尺控件
    mymap.addControl(scaleCtrl)
    var zoomCtrl = new BMapGL.ZoomControl()  // 添加缩放控件
    mymap.addControl(zoomCtrl)

    // 百度3D地图
    var baidumap = new BMapGL.Map('allmap')
    baidumap.centerAndZoom(new BMapGL.Point(106.595219,29.421876), 19)
    baidumap.enableScrollWheelZoom(true)
    baidumap.setHeading(64.5)
    baidumap.setTilt(73)
    var geolocation = new BMapGL.Geolocation()
    geolocation.getCurrentPosition(function(r) {
      if (this.getStatus() == BMAP_STATUS_SUCCESS) {
        var mk = new BMapGL.Marker(r.point)
        baidumap.addOverlay(mk)
        baidumap.panTo(r.point)
        alert('您的位置：' + r.point.lng + ',' + r.point.lat)
      } else {
        alert('failed' + this.getStatus())
      }
    })
    var scaleCtrl = new BMapGL.ScaleControl()  // 添加比例尺控件
    baidumap.addControl(scaleCtrl)
    var zoomCtrl = new BMapGL.ZoomControl()  // 添加缩放控件
    baidumap.addControl(zoomCtrl)


    // 创建地图实例 -- 高德地图
    var map = new AMap.Map('container', {
      resizeEnable: true, //是否监控地图容器尺寸变化
      zoom: 16, //初始地图级别
      center: [106.595219,29.421876], //初始地图中心点
      showIndoorMap: false //关闭室内地图
    })

    var infoWindow = new AMap.InfoWindow({
      anchor: 'top-left',
      content: content.join('<br>')  //传入 dom 对象，或者 html 字符串
    })

    var clickHandler = function(e) {
      //alert('您在[ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]的位置点击了地图！');
      console.log('您在[ ' + e.lnglat.getLng() + ',' + e.lnglat.getLat() + ' ]的位置点击了地图！')

      infoWindow.open(map, [106.595219,29.421876])  //打开窗体
    }

    var marker = new AMap.Marker({  // marker 实例
      position: new AMap.LngLat(106.595219,29.421876),
      title: '重庆工程学院',
    })

    marker.on('click', clickHandler) // marker绑定事件

    map.add(marker) // 将创建的点标记添加到已有的地图实例

  },
  methods: {
  handleClick(tab, event)
  {
    console.log(tab, event)
  }

}
}
</script>

<style scoped>

</style>
