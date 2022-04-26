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
			url: '/admin/core/order/list',
			method: 'get'
		})
	},

	removeById(id) {
		return request({
			url: '/admin/core/order/remove/' + id,
			method: 'delete'
		})
	},

	save(order) {
		return request({
			url: '/admin/core/order/save',
			method: 'put',
			data: order
		})
	},

	getById(id) {
		return request({
			url: '/admin/core/order/get/' + id,
			method: 'get'
		})
	},

	updateById(order) {
		return request({
			url: '/admin/core/order/update',
			method: 'put',
			data: order
		})
	},


	getPageList(page, limit, keyword) {
	    return request({
	      url: `/admin/core/order/list/${page}/${limit}`,
	      method: 'get',
	      params: {keyword}
	    })
	  }

}

