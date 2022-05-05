<template>
  <div class="personal-main">
    <div class="personal-money">
      <h3><i>发布文章</i></h3>
      <br><br>
          <!-- 输入表单 -->
    <el-form label-width="100px">
      <el-form-item label="文章标题">
        <el-input v-model="article.articleName"/>
      </el-form-item>

      <el-form-item label="文章内容">
        <el-input v-model="article.articleText" type='textarea' style="resize:none;" autosize />
      </el-form-item>

      <el-form-item v-if="!article.id">
        <el-button
            type="primary"
            @click="saveData()"
          >
            保存文章信息
          </el-button>
      </el-form-item>
    </el-form>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      article: {}, 
    }
  },

  created () {
    // 当路由中存在id属性的时候，就是回显表单，需要调用回显数据的接口
    // if (this.$route.params.id) {
    //   this.fetchById(this.$route.params.id)
    // }
  },

  methods: {
    // fetchById(id) {
    //   this.$axios.$get('/admin/core/article/get/' + id).then((response) => {
    //     this.article = response.data.record
    //   });
    // },

    saveData() {
      this.$axios.put('/admin/core/article/cSave', this.article).then((response) => {
          this.$router.push('/user/articlemanagement')
      })
    },
  }
}
</script>
