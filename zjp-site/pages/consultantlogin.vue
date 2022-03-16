<template>
  <!--登录-->
  <div class="wrap">
    <div class="content">
      <div class="tdbModule loginPage">
        <div class="registerTitle">咨询师登录</div>
        <div class="registerCont">
          <ul>
            <li>
            <li>
              <input class="input" v-model="consultant.mobile" placeholder="手机号码" />
            </li>

            <li>
              <input class="input" v-model="consultant.password" type="password" placeholder="登录密码" />
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
      consultant: {
        // consultantType: 1,
      },
      isValid: true, //表单校验是否成功
    }
  },

  methods: {
    //登录
    login() {
       this.$axios.$post('/api/core/consultant/login', this.consultant).then((response) => {
        console.log(response)
        cookie.set('user', response.data.consultant)
        window.location.href = '/consultant'
      })
    },
  },
}
</script>


