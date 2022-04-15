import request from '@/utils/request'

export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `/admin/core/consultant/list/${page}/${limit}`,
      method: 'get',
      // data: serachObj  在请求体中传递json post请求使用，需要后端代码也要对应
      params: searchObj
    })
  },

  lock(id, status) {
  	return request({
  		url: `/admin/core/consultant/lock/${id}/${status}`,
  		method: 'put'
  	})
  },

  show(id) {
    return request({
      url: `/admin/core/consultant/show/${id}`,
      method: 'get'
    })
  },

  approval(id, status) {
    return request({
      url: `/admin/core/consultant/approval/${id}/${status}`,
      method: 'put'
    })
  }

}