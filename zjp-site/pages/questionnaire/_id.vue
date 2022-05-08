<template>
  <main>
    <div class="questionnaire-item-content">
      <div class="questionnaire-title">
        <h1><a href="/">枝江心理</a>><a href="/questionnaire">心理问卷</a></h1>
        <h3>{{ questionnaireItem.questionnaireName }}</h3>
        <p class="questionnaire-text">{{ questionnaireItem.questionnaireIntro }}</p>
        <br>
      </div>

      <!-- 是否型 -->
      <div class="question-content"
            v-if="questionnaireItem.questionnaireType == 1">
        <ul class="question-item"            
            v-for="(item, index) in questionItem" 
            :key="index">
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


      <!-- 单选型 -->
      <div class="question-content"
            v-if="questionnaireItem.questionnaireType == 2">
        <ul class="question-item"            
            v-for="(item, index) in questionRadioItem" 
            :key="index">
          <li :prop="'questionRadioItem' + index + 'questionTitle'">
            <span :prop="'questionRadioItem' + index + 'questionNum'">{{ item.questionNum }}.</span> 
            {{ item.questionTitle }}
            <br><br>
            <input type="radio" 
              :name="'questionItemRadio' + index + '0'"
              @click="radioItemChange(index, 0)"
              />
            <label>{{ Object.keys(JSON.parse(item.radioOptions))[0] }}</label>
            
            <div v-for="n in Object.keys(JSON.parse(item.radioOptions)).length-1" >
              <input type="radio" 
              :name="'questionItemRadio' + index + n"
              @click="radioItemChange(index, n)"
              />
              <label>{{ Object.keys(JSON.parse(item.radioOptions))[n] }}</label>
            </div>
            

          </li>
        </ul>
        <button class="questionnaire-submit-btn" @click="radioSubmit(questionnaireItem.id)">完成</button>
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
      let radioQuestion = await $axios.$get('/admin/core/questionnaire/showRadioDetails/' + questionnaireId);

      return {
        questionnaireItem: response.data.record, 
        questionItem: question.data.itemList,
        questionRadioItem: radioQuestion.data.itemList
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
      this.$axios.$post('/api/core/questionnaireWhether/submit', {key1: id, key2: this.radioList}).then((response) => {
        console.log(response);
        console.log(response.data.score);
        // cookie.set('user', response.data.user)
        window.location.href = '/questionnaire/result?id=' + id + '&score=' + response.data.score;
      })
    },

    radioSubmit(id) {
      this.$axios.$post('/api/core/questionnaireRadio/submit', {key1: id, key2: this.radioList}).then((response) => {
        console.log(response);
        console.log(response.data.score);
        // cookie.set('user', response.data.user)
        window.location.href = '/questionnaire/result?id=' + id + '&score=' + response.data.score;
      })
    },
    
  }  
}
</script>