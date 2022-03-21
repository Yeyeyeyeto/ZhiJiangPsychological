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
          <el-form-item label="问卷ID">
            {{ this.$route.params.id }}
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

    </el-form>

    <!-- 1 是否 -->
    <el-form :inline="true">
      <div v-for="questionnaireWhetherItem in questionnaireWhetherItemForm">
        <el-row>
          <el-col :span="2">
            <el-form-item label="题号">
              {{ questionnaireWhetherItem.questionNum }}
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="题干">
              {{ questionnaireWhetherItem.questionTitle }}  
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="选是的分值">
              {{ questionnaireWhetherItem.trueScore }}
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="选否的分值">
              {{ questionnaireWhetherItem.falseScore }}
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-form>


    <!-- 2 单选 -->


    <!-- 3 问答 -->


    <!-- 4 多选 -->


    <!-- 5 混合 -->


    <el-row style="text-align:center">
        <el-button @click="back">
          返回
        </el-button>
    </el-row>

  </div>
</template>

<style>
  .el-form {
    margin-bottom: 40px;
  }
  .margin-left40 {
    margin-left: 40px;
  }
  .input-text input {
    width: 400px;
  }
</style>

<script>
// 引入组件
import questionnaireApi from '@/api/core/questionnaire'

export default {
  data() {
    return {
      questionnaire: {}, // 信息
      questionItemNum: 0,
      questionnaireWhetherItemForm: [
        {
          questionnaireId: 0,
          questionNum: 1,  // 题号
          questionTitle: "",  // 题干
          trueScore: 0, // 选是的分值
          falseScore: 0  // 选否的分值
        }
      ]

    }
  },

  created() {
    if (this.$route.params.id) {
      this.fetchDataById()
    }
  },

  mounted() {
    if (this.$route.params.id) {
      this.loadDetails()
    }
  },

  methods: {
    fetchDataById() {
      questionnaireApi.show(this.$route.params.id).then(response => {
        this.questionnaire = response.data.questionnaireVO;
      });
    },

    loadDetails() {
      questionnaireApi.getItemNum(this.$route.params.id).then(response => {
        this.questionItemNum = response.data.questionItemNum;

        // this.$set(this,'questionItemNum',response.data.questionItemNum);

        console.log(this.questionItemNum);
      });
      console.log("--------" + this.questionItemNum)

      for (var i = 0; i < this.questionItemNum; i++) {
        questionnaireApi.showDetails(this.questionnaireWhetherItemForm[i].questionnaireId).then(response => {
        this.questionnaireWhetherItemForm[i] = response.data.questionnaireWhetherVO;
        });
      };

      for (var i = 0; i < this.questionItemNum; i++) {
        console.log(questionnaireWhetherItemForm[i].questionTitle);
      };

    },

    back() {
      this.$router.push('/core/questionnaire/list');
    },

    addWhetherItem() {
      this.qSum++;
      this.questionnaireWhetherItemForm.push({
          questionNum: this.qSum,  
          questionTitle: "",
          trueScore: 0, 
          falseScore: 0  
      });
    },

    deleteWhetherItem(item, index) {
      this.qSum--;
      this.questionnaireWhetherItemForm.splice(index, 1);
      console.log(this.questionnaireWhetherItemForm, "删除");
    },

  }
}
</script>
