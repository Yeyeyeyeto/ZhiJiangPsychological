<template>
  <!--个人中心-->
  <div class="wrapper wbgcolor">
    <div class="w1200 personal">
      <div class="personal-left">
        <UserNav v-if="type === 1" />
        <ConsultantNav v-if="type === 2" />
      </div>
      <NuxtChild />
      <div class="clear"></div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/user.css'
import UserNav from '~/components/UserNav'
import ConsultantNav from '~/components/ConsultantNav'
import cookie from 'js-cookie'

export default {
  components: {
    UserNav,
    ConsultantNav,
  },

  data() {
    return {
      type: 0,
    }
  },

  mounted() {
    let user = cookie.get('user')
    console.log('11111111111111')
      console.log(user)
      if (user) {
        user = JSON.parse(user)
        this.type = user.type
    }
  }
}
</script>
