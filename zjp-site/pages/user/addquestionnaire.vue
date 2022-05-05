<template>
  <div class="personal-main">
    <div class="personal-money">
      <h3><i>发布问卷</i></h3>
      <br><br>
      <el-form label-width="200px">

      <el-form-item label="问卷名称">
        <el-input v-model="questionnaire.questionnaireName"/>
      </el-form-item>

      <el-form-item label="问卷类型(1是否；2单选；3多选；)">
        <el-input-number v-model="questionnaire.questionnaireType" :min="0" :max="3" />
      </el-form-item>

      <el-form-item label="问卷简介">
        <el-input v-model="questionnaire.questionnaireIntro" type='textarea' style="resize:none;" autosize />
      </el-form-item>

            <el-form-item label="结果说明">
        <el-input v-model="questionnaire.result" type='textarea' style="resize:none;" autosize />
      </el-form-item>

      <el-form-item v-if="!questionnaire.id">
        <el-button
            type="primary"
            @click="saveData()"
          >
            保存文章信息
          </el-button>
      </el-form-item>
    </el-form>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      questionnaire: {}, 
    }
  },

  created () {

  },

  methods: {

    saveData() {
      this.$axios.put('/admin/core/questionnaire/cSave', this.questionnaire).then((response) => {
          this.$router.push('/user/questionnairemanagement')
      })
    },
  }
}
</script>