<template>
  <main>
    <div class="questionnaire-item-content">
      <div class="questionnaire-title">
        <h1><a href="/">枝江心理</a>><a href="/questionnaire">心理问卷</a></h1>
        <h3>{{ questionnaireItem.questionnaireName }}</h3>
        <p class="questionnaire-text">{{ questionnaireItem.questionnaireIntro }}</p>
        <br>
      </div>

      <div class="question-content">
        <ul class="question-item" v-for="(item, index) in questionItem" :key="index">
          <li :prop="'questionItem' + index + 'questionTitle'">
            <span :prop="'questionItem' + index + 'questionNum'">{{ item.questionNum }}.</span> 
            {{ item.questionTitle }}
            <br><br>
            <input type="radio" 
                  :name="'questionItemRadio' + index" 
                  :id="'questionRadio' + index + 't'"
                  @click="radioItemChange(index, 1)"
                  />
                  <label :for="'questionRadio' + index + 't'">是</label>

            <input type="radio" 
                  :name="'questionItemRadio' + index" 
                  :id="'questionRadio' + index + 'f'"
                  @click="radioItemChange(index, 0)"
                  />
                  <label :for="'questionRadio' + index + 'f'">否</label>            
          </li>
        </ul>
        <button class="questionnaire-submit-btn" @click="submit(questionnaireItem.id)">完成</button>
      </div>      

      <div class="questionnaire-item-info">
        <div class="questionnaire-item-info-name" v-if="questionnaireItem.authorId==0">枝江心理官方</div>
        <div class="questionnaire-itme-info-time">{{ questionnaireItem.updateTime }}</div>
      </div>

    </div>
  </main>
</template>

<script>
import '~/assets/css/questionnaire.css'

export default {
  async asyncData({ $axios, params }) {
      let questionnaireId = params.id;
      let response = await $axios.$get('/admin/core/questionnaire/get/' + questionnaireId);
      let question = await $axios.$get('/admin/core/questionnaire/showDetails/' + questionnaireId);

      return {
        questionnaireItem: response.data.record, 
        questionItem: question.data.itemList
      }
  },

  data() {
    return {
      score: [
        {vaule: '1'}
      ],
      radioList: [
      ]
    }
  },

  //此时方法在客户端的浏览器中执行，可以获取到cookie
  mounted() {
  
  },


  methods: {
    radioItemChange(indexIn, value) {
      this.radioList.push({
        index: indexIn+1,
        value: value
      })
    },

    submit(id) {
      console.log(this.radioList);
      console.log(id);
      this.$axios.$post('/api/core/questionnaireWhether/submit', {key1: id, key2: this.radioList}).then((response) => {
        console.log(response)
        // cookie.set('user', response.data.user)
        // window.location.href = '/user'
      })
    },
    
  }  
}
</script>