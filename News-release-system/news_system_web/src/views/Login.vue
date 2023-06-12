<template>
  <div class="userLogin">
    <el-form :rules="rules" ref="loginForm" :model="loginUser" class="loginContainer" @keyup.enter.native="UserLogin">
      <h3 class="loginTitle">
        欢迎登录
      </h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginUser.username" placeholder="亲，请输入用户名">
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginUser.password" placeholder="亲，请输入密码" show-password>
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-row :span="24">
          <el-col :span="12">
            <el-input v-model="loginUser.code" auto-complete="off" placeholder="请输入验证码"></el-input>
          </el-col>
          <el-col :span="12">
            <div class="login-code" @click="refreshCode">
              <!--验证码组件-->
              <s-identify :identifyCode="identifyCode" style="margin-left: 35px"></s-identify>
            </div>
          </el-col>
        </el-row>
      </el-form-item>
      <div style="margin-bottom: 20px;margin-top: 0px">
        <a href="./register" style="margin-left:0px;text-decoration: none;color: #4f84d4">立即注册</a>
                <span @click="dialogFormVisible = true"
                      style="margin-left: 170px;text-decoration: none;color: #4f84d4">忘记密码?</span>
<!--        <span @click="phoneDialogVisible = true"
              style="margin-left: 170px;text-decoration: none;color: #4f84d4">忘记密码?</span>-->
      </div>
      <el-button type="primary" style="width:100%;margin-bottom: 5px" @click="UserLogin">登录</el-button>
      <br>
      <div style="line-height: 22px;margin-top: 10px;color: #9b9b9b;">
        <span style="vertical-align:middle">第三方登录:</span>
        <img src="../assets/icon/qq.png" width="25" height="25" style="vertical-align:middle;margin-left: 8px"
             title="QQ">
        <img src="../assets/icon/weibo.png" width="25" height="25" style="vertical-align:middle;margin-left: 8px"
             title="微博">
        <img src="../assets/icon/github.png" width="25" height="25" style="vertical-align:middle;margin-left: 8px"
             title="GitHub">
      </div>
    </el-form>
    <!-- 找回密码 -->
    <el-dialog @close="clearForm('findPasswordForm')" title="找回密码" :visible.sync="dialogFormVisible" width="40%"
               append-to-body>
      <el-form :model="findPasswordForm" ref="findPasswordForm" :rules="rules" label-width="70px">

        <el-form-item label="邮箱" prop="email">
          <el-input prefix-icon="el-icon-message" placeholder="请填写邮箱" maxlength="32"
                    v-model="findPasswordForm.email"></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请填写 6-18 位密码" type="password" maxlength="18"
                    v-model="findPasswordForm.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input prefix-icon="el-icon-key" placeholder="请填写6位数字验证码" type="number" maxlength="6"
                    v-model="findPasswordForm.code">
            <el-button slot="append" @click="sendEmailCode()" :disabled="disabled">{{ msg }}</el-button>
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" :loading="loading" @click="submitFindPassword('findPasswordForm')">提 交</el-button>
      </div>
    </el-dialog>

    <!-- 修改密码 -->
    <el-dialog title="修改密码" :visible.sync="phoneDialogVisible" width="40%" append-to-body>
      <el-form :model="phoneFindPasswordForm" ref="phoneFindPasswordForm" :rules="rules" label-width="70px">
        <el-form-item label="手机号码" prop="phone">
          <el-input prefix-icon="el-icon-phone" placeholder="请填写手机号码" maxlength="11"
                    v-model="phoneFindPasswordForm.phone"></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请填写 6-18 位密码" type="password" maxlength="18"
                    v-model="phoneFindPasswordForm.password" show-password></el-input>
        </el-form-item>

        <el-form-item label="验证码" prop="code">
          <el-row :gutter="10">
            <el-col :span="14">
              <el-input prefix-icon="el-icon-key"
                        placeholder="请填写6位数字验证码"
                        type="number"
                        maxlength="6"
                        suffix-icon=""
                        v-model="phoneFindPasswordForm.code"></el-input>
            </el-col>
            <el-col :span="10">
              <el-button @click="sendPhoneCode()" :disabled="disabled">{{ msg }}</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="default" @click="phoneDialogVisible = false">取消</el-button>
        <el-button type="danger" :loading="loading" @click="submitPhoneFindPassword('phoneFindPasswordForm')">提 交
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {setRoutes} from "@/router";
import SIdentify from "@/components/SIdentify.vue";
import md5 from "js-md5";
import user from "@/api/user";
import common from "@/api/common";

export default {
  components: {SIdentify},
  name: "userLogin",
  data() {
    var email = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入邮箱'))
      } else if (!/^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/.test(value)) {
        return callback(new Error('请输入正确的邮箱'))
      } else {
        callback()
      }
    };
    var password = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入密码'))
      } else if (/*!/(?=.*[a-zA-Z])[a-zA-Z0-9]{6,18}/.test(value)*/false) {
        return callback(new Error("长度为6-18，只能是数字和字母且至少包含一个字母"))
      } else {
        callback()
      }
    };
    var phone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入手机号'))
      } else if (!/^1[3456789]\d{9}$/.test(value)) {
        return callback(new Error('请输入正确的手机号'))
      } else {
        callback()
      }
    };
    var code = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入6位验证码'))
      } else if (value.length !== 6) {
        return callback(new Error('请输入6位验证码'))
      } else {
        callback()
      }
    }
    return {
      // 倒计时
      disabled: false,
      msg: '点击获取验证码',
      count: 60,
      timer: 0,
      // 按钮加载
      loading: false,
      // 找回密码
      dialogFormVisible: false,
      findPasswordForm: {
        email: '',
        password: '',
        code: ''
      },
      phoneDialogVisible: false,
      phoneFindPasswordForm: {
        phone: '',
        password: '',
        code: ''
      },
      // 登录
      loginUser: {
        userId: "",
        username: "",
        password: "",
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
        }],
        code: [{required: true, message: "请输入验证码", trigger: "blur"}],
        email: [{validator: email, trigger: 'blur'}],
        password: [{validator: password, trigger: 'blur'}],
        phone: [{validator: phone, trigger: 'blur'}],
      },
    }
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods: {
    // 清空表单
    clearForm(formName) {
      this.$refs[formName].resetFields();
    },
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
      // 发送Post请求到服务器验证用户名是否存在
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
    },
    // 修改密码
    submitFindPassword(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          // 请求
          user.findPassword(this.findPasswordForm).then(_ => {
            if (_) {
              // 请求成功
              this.$message({
                message: '密码修改成功',
                type: 'success'
              })
              this.dialogFormVisible = false
            }
          }).finally(_ => {
            this.loading = false
          })
        }
      })
    },
    // 获取验证码
    sendEmailCode() {
      this.$refs.findPasswordForm.validateField('email', result => {
        if (!result) {
          // 按钮倒计时
          this.disabled = true;
          this.msg = this.count-- + 's后重新获取';
          this.timer = setInterval(() => {
            this.msg = this.count-- + 's后重新获取';
            if (this.count < 0) {
              this.msg = '点击获取验证码';
              this.count = 60;
              this.disabled = false;
              clearInterval(this.timer);
            }
          }, 1000);
          // 发送验证码请求
          common.getRequestCode(this.findPasswordForm.email).then(_ => {
            common.getEmailCode(this.findPasswordForm.email, _.data.permissionCode).then(_ => {
              if (_) {
                // 通知邮箱发送
                this.$notify({
                  title: '邮箱验证码已发送',
                  message: '验证码有效时长5分钟, 失效请重新发送',
                  type: 'success',
                  duration: 10 * 1000
                })
              }
            })
          })
        }
      });
    },
    submitPhoneFindPassword(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          // 请求
          user.phoneFindPassword(this.phoneFindPasswordForm).then(_ => {
            if (_) {
              // 请求成功
              this.$message({
                message: '密码修改成功',
                type: 'success'
              })
              this.phoneDialogVisible = false
            }
          }).finally(_ => {
            this.loading = false
          })
        }
      })
    },
    sendPhoneCode() {
      this.$refs.phoneFindPasswordForm.validateField('phone', result => {
        if (!result) {
          // 按钮倒计时
          this.disabled = true;
          this.msg = this.count-- + 's后重新获取';
          this.timer = setInterval(() => {
            this.msg = this.count-- + 's后重新获取';
            if (this.count < 0) {
              this.msg = '点击获取验证码';
              this.count = 60;
              this.disabled = false;
              clearInterval(this.timer);
            }
          }, 1000);

          // 发送验证码请求
          common.getPhoneRequestCode(this.phoneFindPasswordForm.phone).then(_ => {
            common.getPhoneCode(this.phoneFindPasswordForm.phone, _.data.permissionCode).then(_ => {
              if (_) {
                // 通知手机短信发送
                this.$notify({
                  title: '短信验证码已发送',
                  message: '验证码有效时长5分钟, 失效请重新发送',
                  type: 'success',
                  duration: 10 * 1000
                })
              }
            })
          })
        }
      });
    },
  },
}
</script>

<style scoped>
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 380px;
  padding: 15px 35px 15px 35px;
  background: #d3e6e3;
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
  background-image: url("@/assets/images/Tu.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}

.login input[type='number'] {
  -moz-appearance: textfield;
}

span {
  font-size: 16px;
  cursor: pointer;
  display: inline-block;
  margin-top: 5px;
}

.el-dialog {
  padding: 0px 50px;
}

::v-deep input::-webkit-outer-spin-button,
::v-deep input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
}
::v-deep input[type=‘number’] {
  -moz-appearance: textfield !important;
}
</style>