<template>
  <div class="app-container">
	<!-- 表格 -->
	<el-table :data="list" border stripe>
		<el-table-column type="index" width="50" />

		<el-table-column prop="authorId" label="作者ID" width="70" />
		<el-table-column prop="id" label="问卷ID" width="70" />
		<el-table-column prop="questionnaireName" label="问卷名称" width="300" />
		<el-table-column prop="questionnaireIntro" label="问卷简介" :show-overflow-tooltip="true" />
		<el-table-column label="问卷类型" width="100" >
			<template slot-scope="scope">
				<span v-if="scope.row.questionnaireType === 1">是否题</span>
				<span v-if="scope.row.questionnaireType === 2">单选题</span>
				<span v-if="scope.row.questionnaireType === 3">问答题</span>
				<span v-if="scope.row.questionnaireType === 4">多选题</span>
				<span v-if="scope.row.questionnaireType === 5">混合题</span>
			</template>>
		</el-table-column>

      <el-table-column prop="createTime" label="创建时间" width="150" />
      <el-table-column prop="updateTime" label="更新时间" width="150" />
      <el-table-column label="问卷状态" width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" type="danger" size="mini">
            未完善
          </el-tag>
          <el-tag v-else type="success" size="mini">
            正常
          </el-tag>
        </template>
      </el-table-column>
		<el-table-column label="操作" width="200">
			<template slot-scope="scope">
				<router-link v-if="scope.row.status === 1" :to="'/core/questionnaire/edit/' + scope.row.id" style="margin-right:5px;" >
					<el-button type="primary" size="mini" icon="el-icon-edit">
						修改
					</el-button>
				</router-link>
				<router-link v-else :to="'/core/questionnaire/show/' + scope.row.id" style="margin-right:5px;" >
					<el-button type="primary" size="mini" icon="el-icon-edit">
						查看
					</el-button>
				</router-link>
				<el-button type="danger" size="mini" icon="el-icon-delete" @click="removeById(scope.row.id)">删除</el-button>
			</template>
		</el-table-column>
	</el-table>
  </div>
</template>

<script>
import questionnaireApi from '@/api/core/questionnaire'
export default {
	// 定义数据模型
	data() {
		return {
			list: []  // 数据列表
		}
	},

	// 页面渲染成功后获取数据
	created () {
		this.fetchData();
	},

	// 定义方法
	methods: {
		fetchData() {
			// 调用api
			questionnaireApi.list().then(response => {
				this.list = response.data.list;
			})
		},

		// 根据id删除数据
		removeById(id) {
		this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
		    confirmButtonText: '确定',
		    cancelButtonText: '取消',
		    type: 'warning'
		  })
		    .then(() => {
		      return questionnaireApi.removeById(id)
		    })
		    .then(response => {
		        this.$message.success(response.message)
		        this.fetchData()
			})	
		    .catch(error => {
		      if (error === 'cancel') {
		      	this.$message.info('取消删除')
		      }
		    })
		}

	}
}
</script>
