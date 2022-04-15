<template>
  <div class="app-container">
    <el-form label-width="100px" class="form-table">
      <el-row>
        <el-col :span="6">
          <el-form-item label="状态">
            {{ consultant.status }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="创建时间">
            {{ consultant.createTime }}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="姓名">
            {{ consultant.nickName }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="性别">
            {{ consultant.sex }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="年龄">
            {{ consultant.age }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="手机">
            {{ consultant.mobile }}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="咨询师等级">
            {{ consultant.consultantGrade }}
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="咨询地点">
            {{ consultant.consultantLocation }}
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="擅长方向">
            {{ consultant.consultantDirection }}
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="个人签名">
            {{ consultant.consultantMotto }}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="身份证正面">
            <span v-for="item in consultant.consultantAttachVOList" :key="item.id">
              <el-image
                v-if="item.imageType == 'idCard1'"
                style="width: 150px;"
                :src="item.imageUrl"
                :preview-src-list="[item.imageUrl]"
              />
            </span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="身份证反面">
            <span v-for="item in consultant.consultantAttachVOList" :key="item.id">
              <el-image
                v-if="item.imageType == 'idCard2'"
                style="width: 150px;"
                :src="item.imageUrl"
                :preview-src-list="[item.imageUrl]"
              />
            </span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="房产信息">
            <span v-for="item in consultant.consultantAttachVOList" :key="item.id">
              <el-image
                v-if="item.imageType == 'credential'"
                style="width: 150px;"
                :src="item.imageUrl"
                :preview-src-list="[item.imageUrl]"
              />
            </span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="text-align:center">
        <el-button @click="back">
          返回
        </el-button>
      </el-row>
    </el-form>

    <el-form v-if="consultant.consultantAuthStatus === '认证中'" label-width="170px">
      <el-form-item label="是否通过">
        <el-radio-group v-model="status">
          <el-radio :label="2">
            通过
          </el-radio>
          <el-radio :label="-1">
            不通过
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <el-row style="text-align:center">
        <el-button type="primary" @click="approvalSubmit()">
          确定
        </el-button>
      </el-row>
    </el-form>
  </div>
</template>

<script>
// 引入组件
import consultantApi from '@/api/core/consultant'

export default {
  data() {
    return {
      consultant: {}, 
      saveBtnDisabled: false,
      status: 2
    }
  },

  created() {
    if (this.$route.params.id) {
      this.fetchDataById()
    }
  },

  methods: {
    fetchDataById() {
      consultantApi.show(this.$route.params.id).then(response => {
        this.consultant = response.data.consultantDetailVO
      })
    },

    back() {      
      this.$router.push('/core/consultant/list')
    },

    approvalSubmit() {
      this.saveBtnDisabled = true
      consultantApi.approval(this.$route.params.id, this.status).then(response => {
        this.$message.success(response.message)
        this.$router.push('/core/consultant/list')
      })
    }
  }
}
</script>
