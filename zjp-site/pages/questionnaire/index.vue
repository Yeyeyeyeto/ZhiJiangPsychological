<template>
  <main>
    <div class="questionnaire-content">
        <div class="questionnaire-title">
            <h1><a href="/">枝江心理</a>><a href="#">心理问卷</a></h1>
        </div>
			<table width="100%">
				<tbody>
					<tr  class="questionnaire-item" v-for="questionnaireItem in questionnaireItemList" :key="questionnaireItem.id">
            <td class="questionnaire-name"><NuxtLink :to="'/questionnaire/' + questionnaireItem.id">{{ questionnaireItem.questionnaireName }}</NuxtLink></td>
            <td class="questionnaire-id">{{ questionnaireItem.authorId }}</td>
            <td class="questionnaire-time">{{ questionnaireItem.updateTime }}</td>
					</tr>
				</tbody>
			</table>
    </div>
    <div class="questionnaire-info"></div>
  </main>
</template>

<script>
import '~/assets/css/questionnaire.css'

export default {
  async asyncData({ $axios, params }) {
    //投资记录
    let responseQuestionnaireItemList = await $axios.$get(
      '/admin/core/questionnaire/list'
    )

    return {
      questionnaireItemList: responseQuestionnaireItemList.data.list, //投资记录      
    }
  },

  data() {
    return {
    }
  },


  methods: {
    
  }
}


</script>
