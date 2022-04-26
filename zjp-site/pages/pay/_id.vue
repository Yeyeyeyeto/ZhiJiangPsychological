<template>
  <main>
    <div class="consultant-item-content pay-content">            
        <div class="pay-detail">
          <h3>订单详情</h3>
          <div class="pay-detail-item">
            <p>预约老师 <span>{{ consultantItem.nickName }}</span></p>
            <p>咨询次数 <span><el-form><el-form-item><el-input-number v-model="number" :min="1" :max="99" /></el-form-item></el-form></span></p>
            <p>线下咨询地点 <span>{{ consultantItem.consultantLocation }}</span></p>
            <p>咨询费用 <span>¥ {{ consultantItem.consultantCost }}/次</span></p>
            <hr>
          </div>
        </div>
        <div class="pay-btn">          
          <button @click="submit()">提交订单</button>
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
      number: 1,
    }
  },

  //此时方法在客户端的浏览器中执行，可以获取到cookie
  mounted() {
  },


  methods: {
    submit() {
      console.log(this.number);
      console.log(this.$route.params.id);
      this.$axios.$post('/api/core/consultant/paying', {key1: this.$route.params.id, key2: this.number}).then((response) => {
        window.location.href = '/consultant/paying?id=' + this.$route.params.id + '&number=' + response.data.number;
      })
    },
  }  
}
</script>
