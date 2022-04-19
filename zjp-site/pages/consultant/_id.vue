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
        <div class="order-btn"><NuxtLink :to="'/consultant/' + consultantItem.id">立即预约</NuxtLink></div>
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
    
  }  
}
</script>
