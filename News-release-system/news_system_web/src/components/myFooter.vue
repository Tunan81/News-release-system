<template>
  <div>
    <footer class="company-footer" style="background-color: #0a3f89">
      <div class="footer-top">
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <div style="margin-left: 35vh">
              <img src="../assets/front/foot_logo.png" alt="" style="height:100%;width: 100%" />
            </div>
            <div>
              <h2>南泉校区</h2>
              <ul>
                <li><i class="el-icon-location"></i>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：重庆市巴南区</li>
                <li><i class="el-icon-phone"></i>联系电话：13161316999</li>
                <li><i class="el-icon-message"></i>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：xiahaike@icloud.com</li>
              </ul>
            </div>
            <div>
              <h2>双桥校区</h2>
              <ul>
                <li><i class="el-icon-location"></i>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：重庆市大足区</li>
                <li><i class="el-icon-phone"></i>联系电话：13161316723</li>
                <li><i class="el-icon-message"></i>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：xiahaike@icloud.com</li>
              </ul>
            </div>
            <div style="margin-right: 35vh">
              <img src="../assets/front/ewm.jpg" alt="" style="height:100%;width: 100%" />
            </div>
          </div>
      </div>
      <hr style="height: 1px;border: none;border-top: 1px solid white">
      <div class="container">
        <div class="row">
          <div class="col-12" style="text-align: center;">
            <a>版权所有 © 2023 Tunan&nbsp;&nbsp;&nbsp;招生热线：023-62846626&nbsp;|&nbsp;隐私政策&nbsp;|&nbsp;法律信息&nbsp;|&nbsp;网站地图</a>
          </div>
        </div>
        <div class="row">
          <div class="col-12" style="text-align: center;">
            <a>备案号:&nbsp;&nbsp;&nbsp;渝ICP备2022012436号-1</a>
          </div>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "myFooter"
}
</script>

<style scoped>
/*页脚样式*/
/* 全局样式 */
* {
  box-sizing: border-box;
}

body {
  margin: 0;
}

/* footer样式 */
.company-footer {
  background-color: #f8f9fa;
  color: white;
  font-size: 14px;
  line-height: 24px;
}

/* footer-top样式 */
.footer-top {
  padding: 30px 0;
  height: 220px;
}

/* a标签样式 */
a {
  color: inherit;
  text-decoration: none;
}
</style>
