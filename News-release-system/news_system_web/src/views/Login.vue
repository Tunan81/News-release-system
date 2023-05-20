<template>
  <div class="userLogin">
    <el-form :rules="rules" ref="loginForm" :model="loginUser" class="loginContainer">
      <h3 class="loginTitle">
        欢迎登录
      </h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginUser.username" placeholder="亲，请输入用户名">
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginUser.password" placeholder="亲，请输入密码">
        </el-input>
      </el-form-item>
<!--      <el-form-item prop="role">
        <el-select v-model="loginUser.role" placeholder="请选择角色" style="width: 280px">
          <el-option label="一般用户" value="一般用户"></el-option>
          <el-option label="管理员" value="管理员"></el-option>
        </el-select>
      </el-form-item>-->
      <el-form-item prop="code">
        <el-row :span="24">
          <el-col :span="12">
            <el-input v-model="loginUser.code" auto-complete="off" placeholder="请输入验证码"></el-input>
          </el-col>
          <el-col :span="12">
            <div class="login-code" @click="refreshCode">
              <!--验证码组件-->
              <s-identify :identifyCode="identifyCode" style="margin-left: 20px"></s-identify>
            </div>
          </el-col>
        </el-row>
      </el-form-item>
      <a href="./register" style="margin-left:0px;text-decoration: none;color: #4f84d4">立即注册</a>
<!--      <el-checkbox v-model="checked" class="loginRemember">记住我</el-checkbox>-->
      <a href="./RePwd" style="margin-left:145px;text-decoration: none;color: #4f84d4">找回密码</a>
      <br>
      <br>
      <el-button type="primary" style="width:100%;margin-bottom: 5px" @click="UserLogin">登录</el-button>
      <br>
      <div style="line-height: 22px;margin-top: 10px;color: #9b9b9b;">
        <span style="vertical-align:middle">第三方登录:</span>
        <img src="../assets/icon/qq.png" width="25" height="25" style="vertical-align:middle;margin-left: 8px" title="QQ">
        <img src="../assets/icon/weibo.png" width="25" height="25" style="vertical-align:middle;margin-left: 8px" title="微博">
        <img src="../assets/icon/github.png" width="25" height="25" style="vertical-align:middle;margin-left: 8px" title="GitHub">
      </div>
    </el-form>
  </div>
</template>
<script>
import {setRoutes} from "@/router";
import {Axios as request} from "axios";
import SIdentify from "@/components/SIdentify.vue";
import md5 from "js-md5";

export default {
  components: {SIdentify},
  name: "userLogin",
  data() {
    return {
      captchaUrl: "",
      loginUser: {
        userId: "",
        username: "Alice",
        password: "123456",
        avatar: '',
        code: ''
      },
      identifyCodes: '1234567890abcdefjhijklinopqrsduvwxyz',//随机串内容
      identifyCode: '',
      checked: true,
      rules: {
        username: [{required: true, message: "请输入用户名", trigger: "blur"}, {
          min: 3,
          max: 14,
          message: '长度在 3 到 14 个字符',
          trigger: 'blur'
        }
        ],
        password: [{required: true, message: "请输入密码", trigger: "blur"}, , {
          min: 6,
          max: 15,
          message: '密码长度要大于6小于15',
          trigger: 'blur'
        }],
        code: [{required: true, message: "请输入验证码", trigger: "blur"}],
      },
    }

  },
  mounted() {
    // 初始化验证码
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods: {
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    UserLogin() {
      // 校验验证码
      if (this.loginUser.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码')
        this.refreshCode()
        return
      }
      this.loginUser.password = md5(this.loginUser.password) //md5加密
      this.request.post("/user/login", this.loginUser).then(res => {
          if (res.code == "200") {
            //存储用户信息到游览器
            localStorage.setItem("loginUser", JSON.stringify(res.data))
            localStorage.setItem("menus", JSON.stringify(res.data.menus))
            setRoutes()
            this.$message.success("登陆成功！")
            this.$router.push("/home")
          } else if (res.code == "400") {
            this.$message.warning(res.msg)
          } else if (res.code == "401") {
            this.$message.error(res.msg)
          } else {
            this.$message.error(res.msg)
          }
      })
    }
  },
}
</script>

<style scoped>
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #dfe0cd;
  border: 1px solid #99ccd1;
}

.loginTitle {
  color: #f0b593;
  margin: 0px auto 48px auto;
  text-align: center;
  font-size: 40px;
}

.loginRemember {
  text-align: left;
  margin: 0px 0px 15px 0px;
}

.userLogin {
  background-image: url("@/assets/images/userLogin.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}

login-bok {
  width: 30%;
  margin: 150px auto;
  border: 1px solid #DCDFE6;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 30px #DCDFE6;
}
</style>