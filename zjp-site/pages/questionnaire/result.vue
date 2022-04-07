<template>
  <main>
    <div class="questionnaire-result">
    	<div class="questionnaire-result-score">
    		<img src="../../assets/images/good.jpg" width="200px">
    		<p>您已完成问卷评测，得分是{{ score }}。</p>
    	</div>
    	<div class="question-result-text">
    		<p>
                {{ text }}
    		</p>            
    	</div>
    </div>
  </main>
</template>

<script>

import '~/assets/css/result.css'

export default {
  data() {
    return {
        score: this.$route.query.score,
        text: ''
    }
  },

  created() {
    this.$axios.$get('/admin/core/questionnaire/show/' + this.$route.query.id).then((response) => {
        console.log(response.data.questionnaireVO.result)
        this.text = response.data.questionnaireVO.result;
    }) 


  },

  methods: {

  }  
}
</script>
