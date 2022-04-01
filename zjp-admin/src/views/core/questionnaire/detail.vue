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
        <el-col :span="3">
          <el-form-item label="问卷名称">
            {{ questionnaire.questionnaireName }}
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item label="问卷类型">
            {{ questionnaire.questionnaireType }}
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="结果说明">
            {{ questionnaire.questionnaireIntro }}
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

    <br />

    <!-- 1 是否 -->
    <el-form v-if="questionnaire.questionnaireType == 1" ref="questionnaireWhetherItemForm" :inline="true" :model="questionnaireWhetherItemForm">
      <div v-for="(item, index) in questionnaireWhetherItemForm" :key="index">
        <el-row>
          <el-col :span="2">
            <el-form-item label="题号" :prop="'questionnaireWhetherItemForm.' + index + '.questionNum'">
              {{ item.questionNum }}
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="题干" :prop="'questionnaireWhetherItemForm.' + index + '.questionTitle'">
              <el-input v-model="item.questionTitle" class="input-text" />
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="选是的分值" :prop="'questionnaireWhetherItemForm.' + index + '.trueScore'">
              <el-input-number v-model="item.trueScore" />
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="选否的分值" :prop="'questionnaireWhetherItemForm.' + index + '.falseScore'">
              <el-input-number v-model="item.falseScore" />
            </el-form-item>
          </el-col>

          <el-col :span="4">
            <el-form-item>
              <el-button v-if="index+1 == questionnaireWhetherItemForm.length" @click="addWhetherItem" type="primary">增加</el-button>
              <el-button v-if="index !== 0" @click="deleteWhetherItem(item, index)" type="danger">删除</el-button>
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
      <el-button type="primary" @click="detailsWhetherSubmit()">
        确定
      </el-button>
    </el-row>

  </div>
</template>

<style>
/*  .el-form {
    margin-bottom: 40px;
  }
  .margin-left40 {
    margin-left: 40px;
  }
  .input-text input {
    width: 400px;
  }*/
</style>

<script>
// 引入组件
import questionnaireApi from '@/api/core/questionnaire'

export default {
  data() {
    return {
      questionnaire: {}, // 信息
      qSum: 1,
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

  methods: {
    fetchDataById() {
      questionnaireApi.show(this.$route.params.id).then(response => {
        this.questionnaire = response.data.questionnaireVO;
        // this.questionnaireWhetherItemForm.qItem = response.data.questionnaireWhetherVO;
      })
    },

    back() {
      this.$router.push({path: '/core/questionnaire/edit/' + this.$route.params.id});
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

    detailsWhetherSubmit() {
      // this.questionnaireWhetherItemForm.questionnaireId = this.$route.params.id;
      // console.log(this.questionnaireWhetherItemForm[0]);

      for (var i = 0; i < this.questionnaireWhetherItemForm.length; i++) {
        this.questionnaireWhetherItemForm[i].questionnaireId = this.$route.params.id;
        questionnaireApi.detailsWhetherSubmit(this.questionnaireWhetherItemForm[i]);
                    
      }
      
      this.$message.success('提交成功');
      this.$router.push('/core/questionnaire/list');
    },

  }
}
</script>
