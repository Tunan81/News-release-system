<template>
  <div class="userRegist">
    <el-form :rules="rules" ref="loginForm" :model="userRegister" class="loginContainer">
      <h3 class="loginTitle">
        欢迎注册
      </h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="userRegister.username" placeholder="请输入用户名">
        </el-input>
        <div v-if="usernameExist" style="color: red;font-size: 10px">{{ usernameExist }}</div>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="userRegister.password" placeholder="请输入密码">
        </el-input>
      </el-form-item>
      <el-form-item prop="Password">
        <el-input type="password" v-model="userRegister.regRePwd" placeholder="请再次输入密码">
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-row :span="24">
          <el-col :span="12">
            <el-input v-model="userRegister.code" auto-complete="off" placeholder="请输入验证码"></el-input>
          </el-col>
          <el-col :span="12">
            <div class="login-code" @click="refreshCode">
              <!--验证码组件-->
              <s-identify :identifyCode="identifyCode"></s-identify>
            </div>
          </el-col>
        </el-row>
      </el-form-item>
      <el-button type="primary" style="width:100%" @click="submitForm">注册</el-button>
    </el-form>
  </div>
</template>

<script>
import SIdentify from "@/components/SIdentify.vue";

export default {
  components: {SIdentify},
  name: "Regiter",
  data() {
    return {
      userRegister: {
        username: "",
        password: "",
        regRePwd: "",
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
      usernameExist: null, // 用户名是否已存在
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
    submitForm() {
      if (this.userRegister.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码')
        this.refreshCode()
        return
      }
      this.request.post('/user/register', this.userRegister).then(res => {
        if (res.code === "200") {
          this.$message({
            message: '注册成功',
            type: 'success'
          });
          this.$router.push('/login')
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    checkUsername() {
      if (this.userRegister.username) {
        this.request.post('/user/checkUsername', this.userRegister).then(res => {
          if (res.code === "200") {
            this.usernameExist = null
          } else {
            this.usernameExist = res.msg
          }
        })
      }
    }
  },
  watch: {
    // 监听用户名输入框，并设置防抖
    'userRegister.username': _.debounce(function () {
      this.checkUsername()
    }, 1500) //延迟1500毫秒
  }
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

.userRegist {
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