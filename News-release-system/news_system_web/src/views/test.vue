<template>
  <div class="login">
    <div class="login-form">
      <h2>登录</h2>

      <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="70px">

        <el-form-item label="邮箱" prop="email">
          <el-input prefix-icon="el-icon-message" placeholder="请填写邮箱" maxlength="32" v-model="loginForm.email" ></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password"  v-show="!isCode">
          <el-input prefix-icon="el-icon-lock" placeholder="请填写 6-18 位密码" type="password" maxlength="18" v-model="loginForm.password" show-password></el-input>
        </el-form-item>

        <div class="b"><span @click="dialogFormVisible = true">忘记密码？</span></div>

        <el-form-item class="btn-r" label-width="0px">
          <el-button type="danger" icon="el-icon-s-promotion" :loading="loading" @click="submitForm('loginForm')">登 录</el-button>
        </el-form-item>

        <el-form-item class="btn-l" label-width="0px">
          <router-link to="/register">
            <el-button type="success" icon="el-icon-back">注 册</el-button></router-link>
        </el-form-item>

      </el-form>
    </div>

    <!-- 找回密码 -->
    <el-dialog @close="clearForm('findPasswordForm')" title="找回密码" :visible.sync="dialogFormVisible" width="40%">
      <el-form :model="findPasswordForm" ref="findPasswordForm" :rules="rules" label-width="60px">

        <el-form-item label="邮箱" prop="email">
          <el-input prefix-icon="el-icon-message" placeholder="请填写邮箱" maxlength="32" v-model="findPasswordForm.email"></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请填写 6-18 位密码" type="password" maxlength="18" v-model="findPasswordForm.password" show-password></el-input>
        </el-form-item>

        <el-form-item label="验证码" prop="code">
          <el-input prefix-icon="el-icon-key" placeholder="请填写6位数字验证码" type="number" maxlength="6" v-model="findPasswordForm.code">
            <el-button slot="append" @click="sendEmailCode()" :disabled="disabled">{{ msg }}</el-button>
          </el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="danger" :loading="loading" @click="submitFindPassword('findPasswordForm')">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import common from '@/api/common'
import user from '@/api/user'

export default {
  data() {
    var email = (rule, value, callback) => {
      if(!value) {
        return callback(new Error('请输入邮箱'))
      }else if(!/^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/.test(value)) {
        return callback(new Error('请输入正确的邮箱'))
      }else {
        callback()
      }
    };
    var password = (rule, value, callback) => {
      if(!value) {
        return callback(new Error('请输入密码'))
      }else if(!/(?=.*[a-zA-Z])[a-zA-Z0-9]{6,18}/.test(value)) {
        return callback(new Error("密码长度在6-18个字符，只能包含数字、大小写字母 且 至少包含一个字母"))
      }else {
        callback()
      }
    };
    var code = (rule, value, callback) => {
      if(!value) {
        return callback(new Error('请输入6位验证码'))
      }else if(value.length !== 6) {
        return callback(new Error('请输入6位验证码'))
      }else{
        callback()
      }
    }
    return {
      isCode: false,
      // 倒计时
      disabled: false,
      msg: '点击获取验证码',
      count: 60,
      timer: 0,
      // 按钮加载
      loading: false,
      loginForm: {
        email: '',
        password: '',
      },
      // 找回密码
      dialogFormVisible: false,
      findPasswordForm: {
        email: '',
        password: '',
        code: ''
      },
      rules: {
        email: [
          { validator: email, trigger: 'blur' }
        ],
        password: [
          { validator: password, trigger: 'blur' }
        ],
        code: [
          { validator: code, trigger: 'blur' }
        ]
      },
    };
  },
  methods: {
    // 清空表单
    clearForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 登录
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          // 登录请求
          user.login(this.loginForm).then(_ => {
            if(_) {
              // 登录成功
              setTimeout(() => {
                this.$router.push({ path: '/index'})
              }, 500);

              this.$message({
                message: '登录成功',
                type: 'success'
              })
            }
          }).finally(_ => {
            this.loading = false
          })
        }
      });
    },
    // 修改密码
    submitFindPassword(formName) {
      this.$refs[formName].validate((valid) => {
        if(valid) {
          this.loading = true
          // 请求
          user.findPassword(this.findPasswordForm).then(_ => {
            if(_) {
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
        if(!result) {
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
              if(_) {
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
  }
}
</script>

<style>
.login {
  background-color: azure;
  height: 100%;

  display: flex;
  justify-content: center;
  align-items: center;
  background: fixed no-repeat;
  background-size: 100% 100%;
}

.login-form {
  height: 350px;
  width: 600px;
  box-sizing: border-box;
  background-color: #fff;
  opacity: 0.8;
  padding: 20px 50px;
}

.login input::-webkit-outer-spin-button,
.login input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}

.login input[type='number'] {
  -moz-appearance: textfield;
}

.btn-r {
  width: 100px;
  float: right;
}

.btn-l {
  width: 100px;
  float: left;
}

.b span {
  font-size: 12px;
  cursor: pointer;
  display: inline-block;
  margin-top: 5px;
}

.el-dialog {
  padding: 0px 50px;
}
</style>