<template>
  <div class="rePwd">
    <el-form
        class="loginContainer"
        :model="loginForm"
        :rules="loginFormRules"
        ref="loginFormRef"
        label-position="top"
    >
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="loginForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input v-model="loginForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="手机验证码" prop="verificationCode">
        <el-input v-model="loginForm.verificationCode">
          <template slot="append">
            <el-button v-if="loginForm.showloginCode" type="primary" @click="getloginPhoneCode">获取验证码</el-button>
            <div v-else>{{ loginForm.count }}</div>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="login">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: "RePwd",
  data() {
    // 验证手机号是否合法
    var checkMobile = (rules, value, callback) => {
      const regMobile = /^(0|86|17951)?(13[0-9]|15[0123456789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
      if (regMobile.test(value) == true) {
        return callback();
      } else {
        callback(new Error("请输入合法的手机号"));
      }
    };
    // 验证输入的手机号验证码是否和存储的验证码相同
    var checkPhoneCode = (rules, value, callback) => {
      if (value == this.loginForm.contenttext) {
        return callback();
      } else {
        callback(new Error("验证码错误"));
      }
    };
    return {
      // 表单
      loginForm: {
        phone: "",
        password: "",
        verificationCode: "", //表单中展示的验证码
        contenttext: "", //向手机号发送的随机验证码
        timer: null,
        showloginCode: true, //判断展示‘获取验证码’或‘倒计时’
        count: "", //倒计时时间
      },
      // 验证规则
      loginFormRules: {
        phone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { validator: checkMobile, trigger: "blur" },
        ],
        verificationCode: [
          { required: true, message: "请输入手机验证码", trigger: "blur" },
          { validator: checkPhoneCode, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    // 获取手机验证码
    getloginPhoneCode() {
      // 如果未输入手机号，结束执行
      if (this.loginForm.phone == "") {
        return;
      }
      // 获取随机数（4位数字）
      var numCode = "";
      for (var i = 0; i < 4; i++) {
        numCode += Math.floor(Math.random() * 10);
      }
      // 存储发送的验证码,用于验证输入的手机验证码是否和本地存储的相同
      this.loginForm.contenttext = numCode;
      // 向手机号发送验证码传入的参数
      let phoneCode = {
        phonenum: this.loginForm.phone,
        contenttext: "您正在修改密码，验证码为:" + numCode + "，切勿将验证码泄露给他人。",
      };
      // 调用接口，向手机号发送验证码
      this.$axios.post("接口地址", phoneCode).then((res) => {
        if (res.status != 200) {
          return this.$message.error("验证码发送失败！");
        } else {
          // 当验证码发送成功，开始60秒倒计时
          const TIME_COUNT = 60;
          if (!this.loginForm.timer) {
            this.loginForm.showloginCode = false;
            this.loginForm.count = TIME_COUNT;
            this.loginForm.timer = setInterval(() => {
              if (
                  this.loginForm.count > 0 &&
                  this.loginForm.count <= TIME_COUNT
              ) {
                this.loginForm.count -= 1;
              } else {
                this.loginForm.showloginCode = true;
                clearInterval(this.loginForm.timer);
                this.loginForm.timer = null;
              }
            }, 1000);
          }
        }
      });
    },
    // 开始登录
    login() {
      this.$refs.loginFormRef.validate((valid) => {
        if (valid) {
          console.log("开始登录");
        } else {
          console.log("error submit!!");
        }
      });
    },
  },
};
</script>
<style scoped>
.loginForm {
  width: 500px;
  margin: 0 auto;
}
.btns {
  text-align: right;
}

.rePwd {
  background-image: url("@/assets/images/userLogin.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #dfe0cd;
  border: 1px solid #99ccd1;
}
</style>
