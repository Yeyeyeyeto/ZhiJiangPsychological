<template>
  <header>
    <div class="header-top min-width">
      <div class="container fn-clear">
        <!-- 用户未登录 -->
        <ul v-if="!user" class="fn-left my-ul">
          <!-- <li><a href="" :class="'c-orange'">测试</a></li> -->
          <li class="fn-left">
            <div class="logo"><NuxtLink to="/" :class="{ 'c-orange': $route.fullPath === '/' }">枝江心理</NuxtLink></div>
            
          </li>

          <li  class="fn-right login-btn">
            <div>
              <NuxtLink
                to="/login"
                :class="{ 'c-orange': $route.fullPath === '/login' }"
              >
                登录
              </NuxtLink>
            </div>
          </li>

          <li class="fn-right">
            <div>
              <NuxtLink
                to="/register"
                :class="{ 'c-orange': $route.fullPath === '/register' }"
              >
                注册
              </NuxtLink>
            </div>
          </li>

          <li class="fn-right">
            <div class="consultant-btn">
              <NuxtLink
                to="/consultantregister"
                :class="{ 'c-orange': $route.fullPath === '/consultantregister' }"
              >
                咨询师注册
              </NuxtLink>
            </div>
          </li>

        </ul>

        <!-- 用户已登录 -->
        <ul v-if="user" class="fn-left my-ul">
          <li class="fn-left"><div class="logo"><NuxtLink to="/" class="app">枝江心理</NuxtLink></div></li>
          <li class="fn-right">
            <div class="exit">
              <a
                href="javascript:void(0)"
                class="js-login"
                @click="logout()"
                title="退出"
              >
                退出
              </a>
            </div>
          </li>
          <li class="fn-right">
            <div class="">
              <NuxtLink v-if="user.type == 1" to="/user" class="user" title="我的账户">
                <i class="el-icon-user-solid">{{ user.nickName }}</i>
              </NuxtLink>
              <NuxtLink v-if="user.type == 2" to="/user/consultant" class="user" title="我的账户">
                <i class="el-icon-user-solid">{{ user.nickName }}</i>
              </NuxtLink>
            </div>
          </li>
        </ul>  

      </div>
    </div>
  </header>
</template>
<script>
import '~/assets/font/iconfont.css'
import '~/assets/css/header.css'
import cookie from 'js-cookie'

export default {
  data() {
    return {
      user: null,
    }
  },

  mounted() {
    this.showInfo()
  },

  methods: {
    //显示用户信息
    showInfo() {
      // 判断cookie中是否存有用户信息
      let user = cookie.get('user');
      if (!user) {
        console.log('cookie不存在');
        this.user = null;
        return
      }

      user = JSON.parse(user);
      
      // 首先需要校验token是否合法
      this.$axios({
        url: '/api/core/user/checkToken',
        method: 'get',
      }).then((response) => {
        console.log('校验成功');
        this.user = user;
      })
    },

    //退出
    logout() {
      cookie.set('user', '');
      window.location.href = '/login';
    },
  },
}
</script>
