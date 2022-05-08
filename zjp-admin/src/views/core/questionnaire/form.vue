<template>
  <div class="app-container">
  	<!-- 输入表单 -->
    <el-form label-width="200px">
      <el-form-item label="作者ID">
        <el-input v-model="questionnaire.authorId"/>
      </el-form-item>

      <el-form-item label="问卷名称">
        <el-input v-model="questionnaire.questionnaireName"/>
      </el-form-item>

      <el-form-item label="问卷类型(1是否；2单选；3多选；)">
        <el-input-number v-model="questionnaire.questionnaireType" :min="0" :max="3" />
      </el-form-item>

      <el-form-item label="问卷简介">
        <el-input v-model="questionnaire.questionnaireIntro" type='textarea' style="resize:none;" autosize />
      </el-form-item>

	  <!-- <el-form-item label="问卷类型">
        <el-input-inner v-model="questionnaire.questionnaireType" />
      </el-form-item> -->

      <el-form-item label="结果说明">
        <el-input v-model="questionnaire.result" type='textarea' style="resize:none;" autosize />
      </el-form-item>

      <el-form-item v-if="!questionnaire.id">
	  		<el-button
	          type="primary"
	          @click="saveData()"
	        >
	          保存问卷信息
	        </el-button>	
      </el-form-item>

      <el-form-item v-if="questionnaire.id">
	  		<el-button
	          type="primary"
	          @click="updateData()"
	        >
	          保存问卷信息并进行下一步
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
import questionnaireApi from '@/api/core/questionnaire'
export default {
	data() {
		return {
			questionnaire: {},  // 初始化数据
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
			questionnaireApi.getById(id).then(response => {
				this.questionnaire = response.data.record
			})
		},

		saveData() {
			questionnaireApi.save(this.questionnaire).then(response => {
				this.$message.success(response.message)
				this.$router.push('/core/questionnaire/list')
			})
		},

		updateData() {
			questionnaireApi.updateById(this.questionnaire).then(response => {
				this.$message.success(response.message)
				this.$router.push({path: '/core/questionnaire/detail/' + this.questionnaire.id});
			})
		},
	}
}
</script>
