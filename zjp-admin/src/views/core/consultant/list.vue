<template>
  <div class="app-container">
	
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="手机号">
        <el-input v-model="searchObj.mobile" placeholder="手机号" />
  		</el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="fetchData()">
        查询
      </el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 列表 -->
    <el-table :data="list" border stripe>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="id" label="咨询师ID" />
      <el-table-column prop="mobile" label="手机号" />
      <el-table-column prop="nickName" label="咨询师姓名" />
      <!-- <el-table-column prop="idCard" label="身份证号" /> -->
	  <!-- <el-table-column prop="sex" label="咨询师性别" /> -->
  	  <el-table-column label="咨询师性别" >
        <template slot-scope="scope">
	  	  	<span v-if="scope.row.sex === 1">男</span>
	  	  	<span v-if="scope.row.sex === 0">女</span>
  	  	</template>>
  	  </el-table-column>

	  <el-table-column prop="age" label="咨询师年龄" />
	  <el-table-column prop="consultantGrade" label="咨询师等级" />
	  <el-table-column prop="consultantLocation" label="咨询师咨询地点" />
	  <!-- <el-table-column prop="consultantDirection" label="咨询师擅长方向" /> -->

      <el-table-column prop="createTime" label="注册时间" width="160" />
      <el-table-column label="咨询师状态" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" type="danger" size="mini">
            锁定
          </el-tag>
          <el-tag v-else type="success" size="mini">
            正常
          </el-tag>
        </template>
      </el-table-column>

    <el-table-column label="认证状态" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.consultantAuthStatus === 0" type="info" size="mini">
            未认证
          </el-tag>
          <el-tag v-if="scope.row.consultantAuthStatus === 1" type="warning" size="mini">
            认证中
          </el-tag>
          <el-tag v-if="scope.row.consultantAuthStatus === 2" type="success" size="mini">
            认证通过
          </el-tag>
          <el-tag v-if="scope.row.consultantAuthStatus === -1" type="danger" size="mini">
            认证失败
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="200">
	    <template slot-scope="scope">
			<el-button type="primary" size="mini">
				<router-link :to="'/core/consultant/detail/' + scope.row.id">
					查看
				</router-link>
			</el-button>
	        <el-button
	                   v-if="scope.row.status == 1"
	                   type="primary"
	                   size="mini"
	                   @click="lock(scope.row.id, 0)"
	                   >
	            锁定
	        </el-button>
	        <el-button
	                   v-else
	                   type="danger"
	                   size="mini"
	                   @click="lock(scope.row.id, 1)"
	                   >
	            解锁
	        </el-button>
	    </template>
	</el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[10, 20]"
      style="padding: 30px 0; "
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
    />

  </div>
</template>

<script>
import consultantApi from '@/api/core/consultant'
export default {
	data() {
		return {
			list: null,	// 数据列表
			total: 0,	// 数据库中的总记录数
			page: 1,	// 默认页码
			limit: 10,	// 每页记录数
			searchObj: {}	// 查询条件
		}
	},

	created () {
		this.fetchData();
	},

	methods: {
		fetchData() {
			consultantApi.getPageList(this.page, this.limit, this.searchObj).then(response => {
				this.list = response.data.pageModel.records
				this.total = response.data.pageModel.total
			})
		},

		changePageSize(size) {
			console.log('size', size);
			this.limit = size;
			this.fetchData();
		},

		changeCurrentPage(page) {
			console.log('page', page);
			this.page = page;
			this.fetchData();
		},

		resetData() {
			// 还原表单
			this.searchObj = {};
			// 重新查询
			this.fetchData();
		},

		// 锁定和解锁
		lock(id, status) {
			consultantApi.lock(id, status).then(response => {
				this.$message.success(response.message);
				this.fetchData();
			})
		}

	}
}
</script>
