// 引入axios的初始化模块
// @ 符号在vue.config.js 中配置， 表示 'src' 路径的别名
import request from '@/utils/request'

// 导出默认模块
export default {
	// 定义模块成员
	// 成员方法：获取积分等级列表
	list() {
		// 调用axios的初始化模块，发送远程ajax请求
		return request({
			url: '/admin/core/questionnaire/list',
			method: 'get'
		})
	},

	removeById(id) {
		return request({
			url: '/admin/core/questionnaire/remove/' + id,
			method: 'delete'
		})
	},

	save(questionnaire) {
		return request({
			url: '/admin/core/questionnaire/save',
			method: 'put',
			data: questionnaire
		})
	},

	getById(id) {
		return request({
			url: '/admin/core/questionnaire/get/' + id,
			method: 'get'
		})
	},

	updateById(questionnaire) {
		return request({
			url: '/admin/core/questionnaire/update',
			method: 'put',
			data: questionnaire
		})
	},


	getPageList(page, limit, keyword) {
	    return request({
	      url: `/admin/core/questionnaire/list/${page}/${limit}`,
	      method: 'get',
	      params: {keyword}
	    })
	  },

	show(id) {
		return request({
		  url: `/admin/core/questionnaire/show/${id}`,
		  method: 'get'
		})
	},

	detailsWhetherSubmit(questionnaireWhetherItemForm) {
		return request({
		  url: '/admin/core/questionnaire/whetherSubmit',
		  method: 'post',
		  data: questionnaireWhetherItemForm
		})
	}
	
}

