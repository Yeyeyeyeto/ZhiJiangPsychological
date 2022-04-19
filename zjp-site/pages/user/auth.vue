<template>
  <div class="personal-main">
    <div class="personal-pay">
      <h3><i>咨询师身份信息认证</i></h3>
        <el-steps :active="active" style="margin: 40px">
        <el-step title="填写咨询师信息"></el-step>
        <el-step title="提交平台审核"></el-step>
        <el-step title="等待认证结果"></el-step>
      </el-steps>

      <div v-if="active === 0" class="user-borrower">
        <h6>基本信息</h6>
<el-form label-width="120px">
    <el-form-item label="姓名">
        <el-col :span="5">
            <el-input v-model="consultant.nickName" />
        </el-col>
    </el-form-item>
    <el-form-item label="年龄">
        <el-col :span="5">
            <el-input v-model="consultant.age" />
        </el-col>
    </el-form-item>
    <el-form-item label="性别">
        <el-select v-model="consultant.sex">
            <el-option :value="1" :label="'男'" />
            <el-option :value="0" :label="'女'" />
        </el-select>
    </el-form-item>
    <el-form-item label="个人头衔">
        <el-col :span="16">
            <el-input v-model="consultant.consultantGrade" placeholder="XX协会注册会员/硕士/博士/精神科医生/执业医师/三甲医院心理科医生" />
        </el-col>
    </el-form-item>
    <el-form-item label="个人签名">
        <el-col :span="16">
            <el-input v-model="consultant.consultantMotto" placeholder="写咨询师对咨询的理解（价值观）" />
        </el-col>
    </el-form-item>
    <el-form-item label="线下咨询地点">
        <el-col :span="16">
            <el-input v-model="consultant.consultantLocation" />
        </el-col>
    </el-form-item>
    <el-form-item label="擅长咨询方向">
        <el-col :span="16">
            <el-input v-model="consultant.consultantDirection" />
        </el-col>
    </el-form-item>
        <el-form-item label="单次咨询费用">
        <el-col :span="5">
            <el-input v-model="consultant.consultantCost" />
        </el-col>
    </el-form-item>
</el-form>
        

        <h6>身份证件</h6>
<el-form label-width="120px">
    <el-form-item label="身份证人像面">
        <el-upload
                   :on-success="onUploadSuccessIdCard1"
                   :on-remove="onUploadRemove"
                   :multiple="false"
                   :action="uploadUrl"
                   :data="{ module: 'idCard1' }"
                   :limit="1"
                   list-type="picture-card"
                   >
            <i class="el-icon-plus"></i>
        </el-upload>
    </el-form-item>
    <el-form-item label="身份证国徽面">
        <el-upload
                   :on-success="onUploadSuccessIdCard2"
                   :on-remove="onUploadRemove"
                   :multiple="false"
                   :action="uploadUrl"
                   :data="{ module: 'idCard2' }"
                   :limit="1"
                   list-type="picture-card"
                   >
            <i class="el-icon-plus"></i>
        </el-upload>
    </el-form-item>
</el-form>

        <h6>资质证件</h6>
<el-form label-width="120px">
    <el-form-item label="执业资质">
        <el-upload
                   :on-success="onUploadSuccessCredential"
                   :on-remove="onUploadRemove"
                   :multiple="false"
                   :action="uploadUrl"
                   :data="{ module: 'credential' }"
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

      <div v-if="active === 1">
        <div style="margin-top:40px;">
          <el-alert
            title="您的认证申请已成功提交，请耐心等待"
            type="warning"
            show-icon
            :closable="false"
          >
            我们将在2小时内完成审核，审核时间为周一至周五8:00至20:00。
          </el-alert>
        </div>
      </div>

      <div v-if="active === 2">
        <div style="margin-top:40px;">
          <el-alert
            v-if="authStatus === 2"
            title="您的认证审批已通过"
            type="success"
            show-icon
            :closable="false"
          >
          </el-alert>

          <el-alert
            v-if="authStatus === -1"
            title="您的认证审批未通过"
            type="error"
            show-icon
            :closable="false"
          >
          </el-alert>
        </div>
      </div>

    </div>
  </div>
</template>
<script>
export default {
  data() {
    let BASE_API = process.env.BASE_API

    return {
      active: null, //步骤
      authStatus: null,
      submitBtnDisabled: false,
      //借款人信息
      consultant: {
        consultantAttachList: [],
      },
      uploadUrl: BASE_API + '/api/oss/file/upload', //文件上传地址
    }
  },

  created() {
  	this.getUserInfo()
  },

  methods: {
  	getUserInfo() {
  	    this.$axios
  	        .$get('/api/core/consultant/auth/getConsultantStatus')
  	        .then((response) => {
  	        this.authStatus = response.data.authStatus
            console.log(this.authStatus);
  	        if (this.authStatus === 0) {
  	            //未认证
  	            this.active = 0
  	            //获取下拉列表
  	            this.initSelected()
  	        } else if (this.authStatus === 1) {
  	            //认证中
  	            this.active = 1
  	        } else if (this.authStatus === 2) {
  	            //认证成功
  	            this.active = 2
  	        } else if (this.authStatus === -1) {
  	            //认证失败
  	            this.active = 2
  	        }
  	    })
  	},
	
    save() {
      this.submitBtnDisabled = true
	  this.$axios
	    .$post('/api/core/consultant/auth/save', this.consultant)
	    .then((response) => {
	      this.active = 1
	    })
    },

  	onUploadSuccessIdCard1(response, file) {
  		this.onUploadSuccess(response, file, 'idCard1')
  	},

  	onUploadSuccessIdCard2(response, file) {
  		this.onUploadSuccess(response, file, 'idCard2')
  	},

  	onUploadSuccessCredential(response, file) {
  		this.onUploadSuccess(response, file, 'credential')
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
