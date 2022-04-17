<template>
  <main>
    <div class="consultant-item-content">
      <div class="consultant-title">
            <div class="bg"><img src="../../assets/images/bg01.png"></div>
            <div class="head"><img :src="consultantItem.headImg" /></div>
            <h3>{{ consultantItem.nickName }}</h3>
            <div class="motto"><p>{{ consultantItem.consultantMotto }}</p></div>
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
