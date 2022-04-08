<template>
  <div class="app-container">
    <!-- 列表 -->
    <el-table :data="list" border stripe>
	  <el-table-column type="index" width="50" />
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="userId" label="用户ID" />
      <el-table-column prop="questionnaireId" label="咨询师ID" />
      <el-table-column prop="score" label="分数" />      
      <el-table-column prop="createTime" label="测试时间" width="160" />

    </el-table>

  </div>
</template>

<script>
import userApi from '@/api/core/user'
export default {
	data() {
		return {
			list: []
		}
	},

	created () {
		this.fetchData();
	},

	methods: {
		fetchData() {
			// 调用api
			userApi.testList().then(response => {
				this.list = response.data.list;
			})
		},
	}

}
</script>
