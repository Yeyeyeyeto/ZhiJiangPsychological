<template>
  <main>
    <div class="consultant-item-content">
      <div class="consultant-title">
            <div class="bg"><img src="../../assets/images/bg01.png"></div>
            <div class="head"><img :src="consultantItem.headImg" /></div>
            <h3>{{ consultantItem.nickName }}</h3>
            <div class="motto"><p>{{ consultantItem.consultantMotto }}</p></div>
      </div>
      <div class="consultant-item-info">
        <div class="info-intro">
          <h3><span></span>基本信息</h3>
          <div class="info-intro-item">
            <h4>认证资质</h4>
            <p>{{ consultantItem.consultantGrade }}</p>
          </div>
          <div class="info-intro-item">
            <h4>擅长方向</h4>
            <p>{{ consultantItem.consultantDirection }}</p>
          </div>
          <div class="info-intro-item">
            <h4>咨询费用</h4>
            <p>¥ {{ consultantItem.consultantCost }}/次</p>
          </div>
        </div>
        <div class="info-article">
          <h3><span></span>心理文章</h3>
          <div class="info-article-message">暂无文章</div>
        </div>
        <div class="info-process">
          <h3><span></span>咨询流程</h3>
          <div class="info-process-detail">预约咨询师 - 支付订单 - 咨询师确认 - 协商并进行咨询 - 来访者确认完成</div>
        </div>
        <div class="order-btn">
          <NuxtLink :to="'/consultant/' + consultantItem.id">立即预约</NuxtLink>
          <el-form :inline="true" class="demo-form-inline">
            <el-form-item>              
              <el-button
                type="warning"
                @click="commitInvest"
              >
                立即投资
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>      
    </div>
  </main>
</template>

<script>

import '~/assets/css/article.css'

export default {
    async asyncData({ $axios, params }) {
      let consultantId = params.id;
      let response = await $axios.$get('/admin/core/consultant/get/' + consultantId);
      return {
        consultantItem: response.data.record //投资记录      
      }
  },

  data() {
    return {
    }
  },

  //此时方法在客户端的浏览器中执行，可以获取到cookie
  mounted() {
  },


  methods: {
    commitInvest() {
      //校验用户是否登录
      let userInfo = cookie.get('userInfo')
      // console.log(typeof userInfo)
      // console.log(!userInfo) //true
      if (!userInfo) {
        window.location.href = '/login'
        return
      }

      //校验当前用户是否是投资人
      let userInfoObj = JSON.parse(userInfo)
      if (userInfoObj.userType == 2) {
        //借款人
        this.$message.error('借款人无法投资')
        return
      }

      console.log(this.lend.investAmount)
      console.log(this.invest.investAmount)
      console.log(this.lend.amount)
      //判断标的是否超卖：标的已投金额 + 本次投资金额 > 标的总金额
      if (
        this.lend.investAmount + Number(this.invest.investAmount) >
        this.lend.amount
      ) {
        this.$message.error('标的可投资金额不足')
        return
      }

      //是否是100的整数倍
      // console.log(this.invest.investAmount)
      // console.log(Number(this.invest.investAmount))
      // console.log(typeof Number(this.invest.investAmount))
      // return
      if (
        Number(this.invest.investAmount) === 0 ||
        this.invest.investAmount % this.lend.lowestAmount != 0
      ) {
        this.$message.error(`投资金额必须是${this.lend.lowestAmount}的整数倍`)
        return
      }

      //余额的判断
      if (this.invest.investAmount > this.account) {
        this.$message.error('余额不足，请充值')
        return
      }

      //数据提交
      this.$alert(
        '<div style="size: 18px;color: red;">您即将前往汇付宝确认标的</div>',
        '前往汇付宝资金托管平台',
        {
          dangerouslyUseHTMLString: true,
          confirmButtonText: '立即前往',
          callback: (action) => {
            console.log('action', action)
            if (action === 'confirm') {
              this.invest.lendId = this.lend.id
              this.$axios
                .$post('/api/core/lendItem/auth/commitInvest', this.invest)
                .then((response) => {
                  // console.log(response.data.formStr)
                  // debugger
                  document.write(response.data.formStr)
                })
            }
          },
        }
      )
    },

  }  
}
</script>
