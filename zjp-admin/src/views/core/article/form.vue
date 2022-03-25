<template>
  <div class="app-container">
  	<!-- 输入表单 -->
    <el-form label-width="200px">
      <el-form-item label="作者ID">
        <el-input v-model="article.authorId"/>
      </el-form-item>

      <el-form-item label="问卷名称">
        <el-input v-model="article.articleName"/>
      </el-form-item>

      <el-form-item label="结果说明">
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

      <el-form-item v-if="article.id">
	  		<el-button
	          type="primary"
	          @click="updateData()"
	        >
	          修改文章信息
	        </el-button>	
      </el-form-item>
    </el-form>
  </div>
</template>

<style>
	.el-form-item {
		width: 500px;
	}
</style>


<script>
import articleApi from '@/api/core/article'
export default {
	data() {
		return {
			article: {},  // 初始化数据
		}
	},

	created () {
		// 当路由中存在id属性的时候，就是回显表单，需要调用回显数据的接口
		if (this.$route.params.id) {
			this.fetchById(this.$route.params.id)
		}
	},

	methods: {
		fetchById(id) {
			articleApi.getById(id).then(response => {
				this.article = response.data.record
			})
		},

		saveData() {
			articleApi.save(this.article).then(response => {
				this.$message.success(response.message)
				this.$router.push('/core/article/list')
			})
		},

		updateData() {
			articleApi.updateById(this.article).then(response => {
				this.$message.success(response.message)
				this.$router.push('/core/article/list')
			})
		},
	}
}
</script>
