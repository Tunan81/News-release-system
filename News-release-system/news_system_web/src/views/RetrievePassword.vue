<template>
  <div class="forgot-password">
    <h2>找回密码</h2>
    <form @submit.prevent="submitForm">
      <div>
        <label for="username">用户名：</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div>
        <label for="email">邮箱或手机号其中之一：</label>
        <input type="text" id="email" v-model="emailOrPhone" required />
      </div>
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "RetrievePassword",
  data() {
    return {
      username: '',
      emailOrPhone: ''
    };
  },
  methods: {
    submitForm() {
      // 在这里添加逻辑来处理提交表单的行为
      const data = {
        username: this.username,
        emailOrPhone: this.emailOrPhone
      }
      console.log(data)
    }
  }
};
</script>

<style>
.forgot-password {
  max-width: 500px;
  margin: 0 auto;
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input {
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 100%;
}
</style>
