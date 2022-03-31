<template>
  <main>
    <div class="article-item-content">
      <div class="article-title">
            <h1><a href="/">枝江心理</a>><a href="/questionnaire">心理问卷</a></h1>
            <h3>{{ questionnaireItem.questionnaireName }}</h3>
      </div>

      <div class="question-content">
        <ul class="question-item" v-for="(item, index) in questionItem" :key="index">
          <li :prop="'questionItem' + index + 'questionTitle'">
            <span :prop="'questionItem' + index + 'questionNum'">{{ item.questionNum }}.</span> 
            {{ item.questionTitle }}
            <br><br>
            <input type="radio" :name="'questionItemRadio' + index" value="1" :id="'questionRadio' + index + 't'"/><label :for="'questionRadio' + index + 't'">是</label>
            <input type="radio" :name="'questionItemRadio' + index" value="0" :id="'questionRadio' + index + 'f'"/><label :for="'questionRadio' + index + 'f'">否</label>
          </li>
        </ul>
        <button class="btn" @click="submit()">完成</button>
      </div>      


      <div class="article-item-info">
        <div class="article-item-info-name">{{ questionnaireItem.authorId }}</div>
        <div class="article-itme-info-time">{{ questionnaireItem.updateTime }}</div>
      </div>

    </div>
  </main>
</template>

<script>

import '~/assets/css/article.css'

export default {
    async asyncData({ $axios, params }) {
      let questionnaireId = params.id;
      let response = await $axios.$get('/admin/core/questionnaire/get/' + questionnaireId);
      let question = await $axios.$get('/admin/core/questionnaire/showDetails/' + questionnaireId);

      return {
        questionnaireItem: response.data.record, //投资记录      
        questionItem: question.data.itemList
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
    submit() {
      this.$axios.$post().then((response) => {
        
      })
    },
    
  }  
}
</script>
