<template>
  <div class="rePwd">
    <el-form
        class="loginContainer"
        :model="findPasswordForm"
        :rules="rules"
        ref="findPasswordForm"
        label-position="top"
    >
      <h3 class="loginTitle">找回密码</h3>
      <el-form-item label="邮箱" prop="phone">
        <el-input v-model="findPasswordForm.email"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input v-model="findPasswordForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input prefix-icon="el-icon-key" placeholder="请填写6位数字验证码" type="number" maxlength="6"
                  v-model="findPasswordForm.rcode">
          <el-button slot="append" @click="sendEmailCode()" :disabled="disabled">{{ msg }}</el-button>
        </el-input>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="danger" :loading="loading" @click="submitFindPassword('findPasswordForm')">提 交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import user from "@/api/user";
import common from "@/api/common";

export default {
  name: "RePwd",
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
      } else if (!/(?=.*[a-zA-Z])[a-zA-Z0-9]{6,18}/.test(value)) {
        return callback(new Error("密码长度在6-18个字符，只能包含数字、大小写字母 且 至少包含一个字母"))
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
      rules: {
        email: [
          {validator: email, trigger: 'blur'}
        ],
        password: [
          {validator: password, trigger: 'blur'}
        ],
        code: [
          {validator: code, trigger: 'blur'}
        ]
      },
    };
  },
  methods: {
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
  }
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

input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
}

input::-webkit-outer-spin-button {
  -webkit-appearance: none !important;
}

input[type="number"] {
  -moz-appearance: textfield;
}

</style>
