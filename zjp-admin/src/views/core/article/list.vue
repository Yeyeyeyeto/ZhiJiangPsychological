<template>
  <div class="app-container">
	<!-- 表格 -->
	<el-table :data="list" border stripe>
		<el-table-column type="index" width="50" />

		<el-table-column prop="authorId" label="作者ID" width="70" />
		<el-table-column prop="id" label="文章ID" width="70" />
		<el-table-column prop="articleName" label="文章名称" width="700"  />

      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column prop="updateTime" label="更新时间" width="180" />

		<el-table-column label="操作">
			<template slot-scope="scope">
				<router-link :to="'/core/article/edit/' + scope.row.id" style="margin-right:5px;" >
					<el-button type="primary" size="mini" icon="el-icon-edit">
						修改
					</el-button>
				</router-link>
				<el-button type="danger" size="mini" icon="el-icon-delete" @click="removeById(scope.row.id)">删除</el-button>
			</template>
		</el-table-column>
	</el-table>
  </div>
</template>

<script>
import articleApi from '@/api/core/article'
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
			articleApi.list().then(response => {
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
		      return articleApi.removeById(id)
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
