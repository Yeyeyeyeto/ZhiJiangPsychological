<template>
  <div class="personal-main">
    <div class="pmain-profile">
      <div class="pmain-user">
        <div class="user-head">
          <span class="head-img">
            <span>
              <img
                :src="consultantIndexVO.headImg"
                style="width:88px;height:88px;z-index:0;"
              />
              <i class="headframe" style="z-index:0;"></i>
            </span>
          </span>
        </div>
        <div class="user-info">
          <ul>
            <li>用户名<span>{{ consultantIndexVO.nickName }}</span></li>        
            <li>手机号码<span>{{ consultantIndexVO.mobile }}</span></li>
            <li v-if="consultantIndexVO.consultantAuthStatus !== 2">
              您还未经过官方认证
              <NuxtLink to="/user/auth">立即认证</NuxtLink>
              以确保咨询师功能的正常使用。
            </li>
          </ul>
        </div>
      </div>

      <div class="pmain-money">
        
      </div>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      consultantIndexVO: {},
    }
  },

  created() {
    this.fetchUserData()
  },

  methods: {
    fetchUserData() {
      this.$axios
        .$get('/api/core/consultant/auth/getIndexConsultantInfo')
        .then((response) => {
          this.consultantIndexVO = response.data.consultantIndexVO
        })
    },
  },
}
</script>
