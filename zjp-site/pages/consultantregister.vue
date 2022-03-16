<template>
  <!--注册-->
  <div class="wrap">
    <div class="content">
      <div class="registerPage">
        <div v-if="step === 1" class="tdbModule register">
          <div class="registerTitle2">注册咨询师</div>

          <div class="registerCont2">
            <ul>
              <li class="telNumber">
                <input class="input" v-model="consultant.mobile" placeholder="手机号码" />
                <button v-if="!sending" class="button" @click="send()">
                  获取验证码
                </button>
                <button v-else disabled class="button disabled">
                  {{ leftSecond }}秒后重发
                </button>
              </li>

              <li>
                <input class="input" v-model="consultant.code" placeholder="短信验证码" />
              </li>

              <li>
                <input type="password" v-model="consultant.password" class="input" placeholder="密码 6~24个字符 英文数字组成" />
              </li>

              <li class="agree">
                <input type="checkbox" checked />
                我同意《<NuxtLink to="#" target="_black">枝江心理注册协议</NuxtLink>》
              </li>
              <li class="btn">
                <button @click="register()">
                  下一步
                </button>
              </li>
            </ul>
          </div>

          <div class="registerToLogin">
            已有账号? <a href="/consultantlogin">去登录</a>
          </div>
        </div>

        <div v-if="step === 2" class="tdbModule register">
          <div class="registerTitle">注册咨询师</div>
          <div class="registerCont">
            <ul>
              <li class="scses">
                {{ this.consultant.mobile }} 恭喜您注册成功！
                <NuxtLink class="blue" to="/consultantlogin">
                  请登录
                </NuxtLink>
              </li>
            </ul>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/register.css'
export default {
  data() {
    return {
      step: 1, //注册步骤
      consultant: {
        // consultantType: 1,
      },
      sending: false, // 是否发送验证码
      second: 10, // 倒计时间
      leftSecond: 0, //剩余时间
    }
  },

  methods: {
    //发短信
    send() {
      if (!this.consultant.mobile) {
        this.$message.error('请输入手机号')
        return
      }

      // 防止重复提交，显示倒计时
      if (this.sending) return
      this.sending = true

      // 倒计时
      this.timeDown()

      this.$axios.$get('/api/sms/send/' + this.consultant.mobile).then((response) => {
        this.$message.success(response.message)
      }) 
    },

    //倒计时
    timeDown() {
      console.log('倒计时');
      this.leftSecond = this.second;

      const timmer = setInterval(() => {
        this.leftSecond--;
        if (this.leftSecond <= 0) {
          // 停止计时
          clearInterval(timmer);
          this.sending = false;
        }
      }, 1000)
    },

    //注册
    register() {
      this.$axios.$post('/api/core/consultant/register', this.consultant).then((response) => {
        this.step = 2
      })
    },
  },
}
</script>
