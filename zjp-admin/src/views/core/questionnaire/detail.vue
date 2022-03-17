<template>
  <div class="app-container">
    <el-form label-width="100px" class="form-table">
      <el-row>
        <el-col :span="6">
          <el-form-item label="作者ID">
            {{ questionnaire.authorId }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="创建时间">
            {{ questionnaire.createTime }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="更新时间">
            {{ questionnaire.updateTime }}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6">
          <el-form-item label="问卷名称">
            {{ questionnaire.questionnaireName }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="问卷类型">
            {{ questionnaire.questionnaireType }}
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="结果说明">
            {{ questionnaire.result }}
          </el-form-item>
        </el-col>
      </el-row>

      <el-row style="text-align:center">
        <el-button @click="back">
          返回
        </el-button>
      </el-row>
    </el-form>

<!--     <el-form v-if="borrower.status === '认证中'" label-width="170px">
      <el-form-item label="是否通过">
        <el-radio-group v-model="approvalForm.status">
          <el-radio :label="2">
            通过
          </el-radio>
          <el-radio :label="-1">
            不通过
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item v-if="approvalForm.status == 2" label="基本信息积分">
        <el-input v-model="approvalForm.infoIntegral" style="width: 140px;" />
        <span style="color: indianred">（可获取30至100积分）</span>
      </el-form-item> -->

      <!-- 1 是否 -->
      <el-form label-width="100px" class="form-table">
        <el-row>
          <el-col :span="2">
            <el-form-item label="题号" >
              {{ questionnaireItemForm.qid }}
            </el-form-item>
          </el-col>

          <el-col :span="">
            <el-form-item label="题干">
              <el-input v-model="questionnaireItemForm.qid" type='textarea' />
            </el-form-item>  
          </el-col>

          <el-col :span="4">
            <el-form-item label="选是的分值">
              <el-input-number v-model="questionnaire.questionnaireType" />
            </el-form-item>
          </el-col>

          <el-col :span="4">
            <el-form-item label="选否的分值">
              <el-input-number v-model="questionnaire.questionnaireType" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row style="text-align:center">
          <el-button @click="add">
            增加
          </el-button>
        </el-row>
      </el-form>

      <!-- 2 单选 -->


      <!-- 3 问答 -->


      <!-- 4 多选 -->


      <!-- 5 混合 -->


      <el-row style="text-align:center">
        <el-button type="primary" @click="approvalSubmit()">
          确定
        </el-button>
      </el-row>
    </el-form>
  </div>
</template>

<style>
  .el-form {
    margin-bottom: 40px;
  }
</style>

<script>
// 引入组件
import questionnaireApi from '@/api/core/questionnaire'

export default {
  data() {
    return {
      questionnaire: {}, // 信息
      // approvalForm: { // 审批表单
      //   questionnaireId: 0,
      //   status: 2,
      //   content: '',
      //   infoIntegral: 30,
      //   isIdCardOk: false,
      //   isHouseOk: false,
      //   isCarOk: false
      // }，
      questionnaireItemForm: {
        qSum: 0,
        qItem: {
          qid: 0
        }
      }
    }
  },

  created() {
    if (this.$route.params.id) {
      this.fetchDataById()
    }
  },

  methods: {
    fetchDataById() {
      questionnaireApi.show(this.$route.params.id).then(response => {
        this.questionnaire = response.data.questionnaireVO
      })
    },

    back() {
      // this.$router.push({path: '/core/questionnaire/list'})
      this.$router.push('/core/questionnaire/list')
    },

    approvalSubmit() {
      this.saveBtnDisabled = true
      this.approvalForm.questionnaireId = this.$route.params.id
      questionnaireApi.approval(this.approvalForm).then(response => {
        this.$message.success(response.message)
        this.$router.push('/core/questionnaire/list')
      })
    }
  }
}
</script>
