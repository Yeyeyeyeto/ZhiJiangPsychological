<template>
  <div class="personal-main">
    <div class="pmain-profile">
      <div class="pmain-user">
        <div class="user-head">
          <span class="head-img">
            <span>
              <img
                :src="userIndexVO.headImg"
                style="width:84px;height:84px;z-index:0;"
              />
              <i class="headframe" style="z-index:0;"></i>
            </span>
          </span>
        </div>
        <div class="user-info">
          <ul>
            <li>用户名<span>{{ userIndexVO.nickName }}</span></li>            
            <li>手机号码<span>{{ userIndexVO.mobile }}</span></li>
          </ul>
        </div>
      </div>

      <div class="pmain-money">
        <ul>
          <li class="none">
            <span>
              <em>测试次数</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f26 fb">{{ userIndexVO.testNumber }}</i>
            </span>
          </li>
          <li>
            <span>
              <em>咨询次数</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f26 fb">{{ userIndexVO.ConsultingNumber }}</i>
            </span>
          </li>
        </ul>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userIndexVO: {},
    }
  },

  created() {
    this.fetchUserData()
  },

  methods: {
    fetchUserData() {
      this.$axios
        .$get('/api/core/user/auth/getIndexUserInfo')
        .then((response) => {
          this.userIndexVO = response.data.userIndexVO
        })
    },
  },
}
</script>
