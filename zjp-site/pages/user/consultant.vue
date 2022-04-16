<template>
  <div class="personal-main">
    <div class="pmain-profile">
      <div class="pmain-user">
        <div class="user-head">
          <span class="head-img">
            <span>
              <NuxtLink to="/user/auth">
              <img
                :src="consultantIndexVO.headImg"
                style="width:88px;height:88px;z-index:0;"
              />
              <i class="headframe" style="z-index:0;"></i>
              </NuxtLink>
            </span>
          </span>
        </div>
        <div class="user-info">
          <ul>
            <li>用户名<span>{{ consultantIndexVO.nickName }}</span></li>        
            <li>手机号码<span>{{ consultantIndexVO.mobile }}</span></li>
            <li v-if="consultantIndexVO.status !== 2">
              您还未经过官方认证
              <NuxtLink to="/user/auth">立即认证</NuxtLink>
              以确保咨询师功能的正常使用。
            </li>
          </ul>
        </div>
      </div>

      <div class="pmain-money pmain-li" v-if="consultantIndexVO.status == 2">
        <ul>
          <li class="none">
            <span>
              <em>咨询师等级</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f18 fb">{{ consultantIndexVO.consultantGrade }}</i>
            </span>
          </li>
          <li>
            <span>
              <em>个人签名</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f18 fb">{{ consultantIndexVO.consultantMotto }}</i>
            </span>
          </li>
          <li>
            <span>
              <em>擅长方向</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f18 fb">{{ consultantIndexVO.consultantDirection }}</i>
            </span>
          </li>
          <li>
            <span>
              <em>咨询地点</em>
              <i class="markicon"></i>
            </span>
            <span class="truemoney">
              <i class="f18 fb">{{ consultantIndexVO.consultantLocation }}</i>
            </span>
          </li>
        </ul>
      </div>

      <div class="pmain-money" v-if="consultantIndexVO.status == 2">
        <ul>
          <li class="none">
            <span>
              <h3>其他设置</h3>
              <br>
            </span>
            <NuxtLink to="/user/headimg">修改照片</NuxtLink>
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
