<template>
  <main>
	<!-- 心理阅读 -->
    <div class="container container-white">
    	<div class="psy-read">
    		<div class="main-title">
    			<a href="/article" class="common-a">
    				<span class="psy-answer">心理阅读</span>
    			</a>
	    		<span class="sub-title">心理学的世界等你来探索</span>
    		</div>
    	</div>
    	<div class="content-read">
            <tbody>
                <tr  class="article-item" v-for="(articleItem, index) in articleItemList" v-if="index<10" :key="articleItem.id">
                    <td class="article-name"><NuxtLink :to="'/article/' + articleItem.id">{{ articleItem.articleName }}</NuxtLink></td>
                    <td class="article-time">{{ articleItem.updateTime }}</td>
                </tr>
            </tbody>   
        </div>
    </div>

  	<!-- 心理测试 -->
    <div class="container container-white">
    	<div class="psy-test">
    		<div class="main-title">
    			<a href="/questionnaire" class="common-a">
    				<span class="psy-answer">心理问卷</span>
    			</a>
    			<span class="sub-title">认识自我 了解他人</span>
    		</div>    		
    	</div>
    	<div class="content-test">
         <tbody>
                <tr  class="questionnaire-item" v-for="(questionnaireItem, index) in questionnaireItemList" 
                    
                    :key="questionnaireItem.id">
                    <td class="questionnaire-name"><NuxtLink :to="'/questionnaire/' + questionnaireItem.id">{{ questionnaireItem.questionnaireName }}</NuxtLink></td>
                    <td class="questionnaire-intro">{{ questionnaireItem.questionnaireIntro }}</td>
                    <td class="questionnaire-time">{{ questionnaireItem.updateTime }}</td>
                </tr>
            </tbody>      
        </div>
    </div>

  	<!-- 心理咨询 -->
    <div class="container container-white">
    	<div class="psy-consult">
    		<div class="main-title">
    			<a href="/consultant" class="common-a">
    				<span class="psy-answer">心理咨询</span>
    			</a>
	    		<span class="sub-title">没有人是一座孤岛，每个人都需要心理咨询</span>
    		</div>
    	</div>
    	<div class="content-consult">
        <tbody>
            <tr  class="consultant-item" v-for="(consultantItem, index) in consultantItemList" v-if="index<8" :key="consultantItem.id">
                <td class="consultant-hidden-img">
                  <img :src="consultantItem.headImg"/>                      
                </td>
                <td class="consultant-head">
                  <img :src="consultantItem.headImg"/>   
                </td>
                <td class="consultant-name">{{ consultantItem.nickName }}</td>

                <td class="consultant-sex">
                  <span v-if="consultantItem.sex == 1">男</span>
                  <span v-if="consultantItem.sex !== 1">女</span>
                  <span>{{ consultantItem.age }}</span>
                </td>
                <td class="consultant-grade">{{ consultantItem.consultantGrade }}</td>
                <td class="consultant-direction">{{ consultantItem.consultantDirection }}</td>
                <td class="consultant-motto"><p>{{ consultantItem.consultantMotto }}</p></td>
                <td class="consultant-btn"><NuxtLink :to="'/consultant/' + consultantItem.id">预约</NuxtLink></td>
            </tr>
        </tbody>  
      </div>
    </div>


  	<!-- 心理问答 -->
<!--     <div class="container container-white">
    	<div class="psy-askanswer">
    		<div class="main-title">
    			<a href="#" class="common-a">
    				<span class="psy-answer">心理问答</span>
    			</a>
	    		<span class="sub-title">每个人的心事都能找到答案</span>
    		</div>
    	</div>
    	<div class="content-skanswer"></div>
    </div> -->

  </main>
</template>

<script>
import '~/assets/css/index.css'
import '~/assets/css/article.css'


export default {
  async asyncData({ $axios, params }) {
    let responseArticleItemList = await $axios.$get(
      '/admin/core/article/list'
    )
    let responseQuestionnaireItemList = await $axios.$get(
      '/admin/core/questionnaire/list'
    )
    let responseConsultantItemList = await $axios.$get(
      '/admin/core/consultant/list'
    )

    return {
      articleItemList: responseArticleItemList.data.list,    
      questionnaireItemList: responseQuestionnaireItemList.data.list,    
      consultantItemList: responseConsultantItemList.data.list,
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
