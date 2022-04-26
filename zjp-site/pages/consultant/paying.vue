<template>
  <main>
    <div class="questionnaire-result">
    	<div class="questionnaire-result-number">    		
    		<p>咨询师：{{ cName }}<span>¥ {{ number }}</span></p>     
        <p class="p-img"><img src="../../assets/images/code-pay.png"></p>
    	</div>    
      <div class="pay-btn">          
          <button @click="submit()">支付完成</button>
      </div>	
    </div>
  </main>
</template>

<script>

import '~/assets/css/result.css'

export default {
  data() {
    return {
        number: this.$route.query.number,
        cName: '',
        cId: 0
    }
  },

  created() {
    this.$axios.$get('/admin/core/consultant/show/' + this.$route.query.id).then((response) => {
        this.cName = response.data.consultantDetailVO.nickName;
        this.cId = response.data.consultantDetailVO.id;
    })
  },

  methods: {
    submit() {
      this.$axios.$post('/admin/core/order/save', {key1: this.cId, key2: this.number}).then((response) => {
        window.location.href = '/user';
      })
    },
  }  
}
</script>