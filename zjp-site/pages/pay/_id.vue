<template>
  <main>
    <div class="consultant-item-content pay-content">            
        <div class="pay-detail">
          <h3>订单详情</h3>
          <div class="pay-detail-item">
            <p>预约老师 <span>{{ consultantItem.nickName }}</span></p>
            <p>咨询次数 <span><el-form><el-form-item><el-input-number v-model="count" :min="1" :max="99" /></el-form-item></el-form></span></p>
            <p>线下咨询地点 <span>{{ consultantItem.consultantLocation }}</span></p>
            <hr>
            <span class="pay-cost">¥ {{ consultantItem.consultantCost }}/次</span>
          </div>
        </div>

        <div class="pay-btn">
          <NuxtLink :to="'/consultant/' + consultantItem.id">提交订单</NuxtLink>
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
      count: 1
    }
  },

  //此时方法在客户端的浏览器中执行，可以获取到cookie
  mounted() {
  },


  methods: {

  }  
}
</script>
