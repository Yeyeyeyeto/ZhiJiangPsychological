<template>
  <main>
    <div class="article-content">
        <div class="article-title">
            <h1><a href="/">枝江心理</a>><a href="#">心理文章</a></h1>
        </div>
			<table width="100%">
				<tbody>
					<tr  class="article-item" v-for="articleItem in articleItemList" :key="articleItem.id">
						<td class="article-name"><NuxtLink :to="'/article/' + articleItem.id">{{ articleItem.articleName }}</NuxtLink></td>
						<td class="article-text">{{ articleItem.articleText }}</td>
            <td class="article-id" v-if="articleItem.authorId==0">枝江心理官方</td>
						<td class="article-time">{{ articleItem.updateTime }}</td>
					</tr>
				</tbody>
			</table>
    </div>
    <div class="article-info"></div>
  </main>
</template>

<script>
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

  //此时方法在客户端的浏览器中执行，可以获取到cookie
  mounted() {
  },

 //  filters:{
	// ellipsis(value){
	// 	if (!value) return '';
	// 	if (value.length > 100) {
	// 		return value.slice(0,100) + '...'
	// }
	// return value
 //  },

  methods: {
    
  }
}


</script>
