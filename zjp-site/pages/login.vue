<template>
  <!--登录-->
  <div class="wrap1">
    <div class="content1">
      <div class="tdbModule loginPage">
        <div class="registerTitle">用户登录</div>
        <div class="registerCont">
          <ul>
            <li>
            <li>
              <!-- <span class="dis">手机号：</span> -->
              <input class="input" v-model="user.mobile" placeholder="手机号码" />
            </li>

            <li>
              <!-- <span class="dis">密码：</span> -->
              <input class="input" v-model="user.password" type="password" placeholder="登录密码" />
            </li>
            <li class="btn">
              <button @click="login()" :class="{ disabled: !isValid }">
                立即登录
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import '~/assets/css/register.css'
import cookie from 'js-cookie'

export default {
  data() {
    return {
      user: {
        // userType: 1,
      },
      isValid: true, //表单校验是否成功
    }
  },

  methods: {
    //登录
    login() {
       this.$axios.$post('/api/core/user/login', this.user).then((response) => {
        console.log(response)
        cookie.set('user', response.data.user)
        window.location.href = '/user'
      })
    },
  },
}
</script>


