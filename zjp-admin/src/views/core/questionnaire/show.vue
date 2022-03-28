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
    <el-table :data="itemList">
      <el-table-column prop="questionNum" label="题号"/>
      <el-table-column prop="questionTitle" label="题干" />
      <el-table-column prop="trueScore" label="是的分值" />
      <el-table-column prop="falseScore" label="否的分值" />
    </el-table>


    <!-- 2 单选 -->


    <!-- 3 问答 -->


    <!-- 4 多选 -->


    <!-- 5 混合 -->


    <el-row style="text-align:center">
        <br>
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
      itemList: [],
      questionnaire: {}, // 信息
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
    
  },

  methods: {
    fetchDataById() {
      questionnaireApi.show(this.$route.params.id).then(response => {
        this.questionnaire = response.data.questionnaireVO;
      });
      questionnaireApi.showDetails(this.$route.params.id).then(response => {
        this.itemList = response.data.itemList;
      })
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
