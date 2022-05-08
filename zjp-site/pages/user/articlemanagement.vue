<template>
  <div class="personal-main">
    <div class="personal-money">
      <h3><i>文章管理</i></h3>

      <div class="personal-moneylist" style="margin-top: 40px;">
        <div class="pmain-contitle">
          <span class="pmain-title1 fb" style="width: 100px;">
            文章ID
          </span>
          <span class="pmain-title2 fb" style="width: 300px;">
            文章标题
          </span>
          <span class="pmain-title3 fb" style="width: 150px;">
             创建时间
          </span>
          <span class="pmain-title3 fb" style="width: 140px;">
             操作
          </span>
        </div>
        <ul>
          <li
            v-for="item in list"
            :key="item.id"
            style="width:100%"
          >
            <span class="pmain-title1 pmain-height" style="width: 100px;">
              {{ item.id }}
            </span>
            <span class="pmain-title2 pmain-height" style="width: 300px;">
              {{ item.articleName }}
            </span>
            <span class="pmain-title3 pmain-height" style="width: 150px;">
              {{ item.createTime }}
            </span>
            <span class="pmain-title3 pmain-height" style="width: 160px;">
              <span><button class="action-btn" @click="alertArticle(item.id)" >修改</button></span>              
              <span><button class="action-btn" @click="deleteArticle(item.id)">删除</button></span>
            </span>
          </li>
        </ul>

          <button class="add-btn" v-if="status === 2">
            <NuxtLink to="/user/addarticle">发布文章</NuxtLink>
          </button>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      status: 0,
    }
  },

  created() {
    this.fetchArticleList()
  },

  methods: {
    fetchArticleList() {
      this.$axios.$get('/admin/core/article/consultantList').then((response) => {
        this.list = response.data.list
      });
      this.$axios.$get('/admin/core/consultant/getAuthStatus').then((response) => {
        this.status = response.data.authStatus
      });
    },
    deleteArticle(id) {
      this.$axios.$delete('/admin/core/article/remove/' + id).then((response) => {
        location.reload();
      })
    },
  }
}
</script>