<template>
  <div class="personal-main">
    <div class="personal-pay">
      <h3><i>头像修改</i></h3>

        <br><br><br>

        <div class="user-head">
          <span class="show-head-img">
              <img
                :src="consultantIndexVO.headImg"
                style="width:160px;height:160px;z-index:0;"
              />
          </span>
        </div>

        <el-form label-width="120px">
            <el-form-item label="上传新照片">
                <el-upload
                           :on-success="onUploadSuccessIMG"
                           :on-remove="onUploadRemove"
                           :multiple="false"
                           :action="uploadUrl"
                           :data="{ module: 'img' }"
                           :limit="1"
                           list-type="picture-card"
                           >
                    <i class="el-icon-plus"></i>
                </el-upload>
            </el-form-item>
        </el-form>

        <el-form label-width="120px">
          <el-form-item>
            <el-button
              type="primary"
              :disabled="submitBtnDisabled"
              @click="save"
            >
              提交
            </el-button>
          </el-form-item>
        </el-form>

    </div>
  </div>
</template>
<script>
export default {
  data() {
    let BASE_API = process.env.BASE_API

    return {
      submitBtnDisabled: false,
      consultant: {
        consultantAttachList: [],
      },
      uploadUrl: BASE_API + '/api/oss/file/upload', //文件上传地址
      consultantIndexVO: {},
    }
  },

  created() {
  	 this.fetchUserData()
  },

  methods: {
    fetchUserData() {
      this.$axios
        .$get('/api/core/consultant/auth/getIndexConsultantInfo')
        .then((response) => {
          this.consultantIndexVO = response.data.consultantIndexVO
        })
    },
	
    save() {
      this.submitBtnDisabled = true
	  this.$axios
	    .$post('/api/core/consultant/updateImg', this.consultant.consultantAttachList[0].imageUrl)
	    .then((response) => {
	      this.$router.push("/user/consultant");
	    })
    },

  	onUploadSuccessIMG(response, file) {
  		this.onUploadSuccess(response, file, 'img')
  	},

  	onUploadSuccess(response, file, type) {
  		if (response.code === 0) {
  			// 业务成功,填充borrow.borrowAttachList列表
  			this.consultant.consultantAttachList.push({
  				imageName: file.name,
  				imageUrl: response.data.url,
  				imageType: type,
  			})
  		} else {
  			// 业务失败
  			this.$message.error(response.message);
  		}
  	},

  	onUploadRemove(file, fileList) {
  		// 调用远程文件删除接口
  		this.$axios.delete('api/oss/file/remove?url=' + file.response.data.url).then((response) => {
  			// 从this.consultant.borrwerAttachList列表中删除对象
  			this.consultant.consultantAttachList = this.consultant.consultantAttachList.filter(
  				function(item) {
  					return item.imageUrl != file.response.data.url
  				})
  		})
  	},

  },
}
</script>
