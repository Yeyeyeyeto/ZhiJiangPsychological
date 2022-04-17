<template>
  <main>
    <div class="article-content">
        <div class="article-title">
            <h1><a href="/">枝江心理</a>><a href="#">心理咨询</a></h1>
        </div>
			<table width="100%">
				<tbody>					
          <tr  class="consultant-item" v-for="(consultantItem, index) in consultantItemList" :key="consultantItem.id" v-if="consultantItem.consultantAuthStatus == 2">              
              <td class="consultant-head">
                <img :src="consultantItem.headImg"/>   
              </td>
              <td class="consultant-name">{{ consultantItem.nickName }}</td>
              <td class="consultant-sex">
                <span v-if="consultantItem.sex == 1">男</span>
                <span v-if="consultantItem.sex !== 1">女</span>
                <span>{{ consultantItem.age }}</span>
              </td>
              <td class="consultant-location"><p>{{ consultantItem.consultantLocation }}</p></td>
              <td class="consultant-grade">{{ consultantItem.consultantGrade }}    擅长方向: {{ consultantItem.consultantDirection }}</td>              
              <td class="consultant-btn"><NuxtLink :to="'/consultant/' + consultantItem.id">预约</NuxtLink></td>
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
    let responseConsultantItemList = await $axios.$get(
      '/admin/core/consultant/list'
    )

    return {
      consultantItemList: responseConsultantItemList.data.list,
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
