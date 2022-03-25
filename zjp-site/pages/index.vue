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
    			<a href="#" class="common-a">
    				<span class="psy-answer">心理测试</span>
    			</a>
    			<span class="sub-title">认识自我 了解他人</span>
    		</div>    		
    	</div>
    	<div class="content-test"></div>
    </div>

  	<!-- 心理咨询 -->
    <div class="container container-white">
    	<div class="psy-consult">
    		<div class="main-title">
    			<a href="#" class="common-a">
    				<span class="psy-answer">心理咨询</span>
    			</a>
	    		<span class="sub-title">没有人是一座孤岛，每个人都需要心理咨询</span>
    		</div>
    	</div>
    	<div class="content-consult"></div>
    </div>


  	<!-- 心理问答 -->
    <div class="container container-white">
    	<div class="psy-askanswer">
    		<div class="main-title">
    			<a href="#" class="common-a">
    				<span class="psy-answer">心理问答</span>
    			</a>
	    		<span class="sub-title">每个人的心事都能找到答案</span>
    		</div>
    	</div>
    	<div class="content-skanswer"></div>
    </div>

  </main>
</template>

<script>
import '~/assets/css/index.css'
import '~/assets/css/article.css'


export default {
  async asyncData({ $axios, params }) {
    //投资记录
    let responseArticleItemList = await $axios.$get(
      '/admin/core/article/list'
    )

    return {
      articleItemList: responseArticleItemList.data.list, //投资记录      
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
