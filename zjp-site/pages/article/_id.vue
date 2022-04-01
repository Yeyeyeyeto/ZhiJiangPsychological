<template>
  <main>
    <div class="article-item-content">
      <div class="article-title">
            <h1><a href="/">枝江心理</a>><a href="/article">心理文章</a></h1>
            <h3>{{ articleItem.articleName }}</h3>
      </div>
      <div class="article-item-content-text">
        {{ articleItem.articleText }}
      </div>
      <div class="article-item-info">
        <div class="article-item-info-name" v-if="articleItem.authorId==0">枝江心理官方</div>
        <div class="article-itme-info-time">{{ articleItem.updateTime }}</div>
      </div>


    </div>
  </main>
</template>

<script>

import '~/assets/css/article.css'

export default {
    async asyncData({ $axios, params }) {
      let articleId = params.id;
      let response = await $axios.$get('/admin/core/article/get/' + articleId);
      return {
        articleItem: response.data.record //投资记录      
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
