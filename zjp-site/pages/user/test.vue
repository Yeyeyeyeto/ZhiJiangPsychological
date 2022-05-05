<template>
  <div class="personal-main">
    <div class="personal-money">
      <h3><i>测试记录</i></h3>

      <div class="personal-moneylist" style="margin-top: 40px;">
        <div class="pmain-contitle">
          <span class="pmain-title1 fb" style="width: 150px;">
            测试时间
          </span>
          <span class="pmain-title2 fb" style="width: 120px;">
            测试问卷ID
          </span>
          <span class="pmain-title3 fb" style="width: 160px;">
             测试结果
          </span>
        </div>
        <ul>
          <li
            v-for="testItem in testList"
            :key="testItem.id"
            style="width:100%"
          >
            <span class="pmain-title1 pmain-height" style="width: 150px;">
              {{ testItem.createTime }}
            </span>
            <span class="pmain-title2 pmain-height" style="width: 120px;">
              {{ testItem.questionnaireId }}
            </span>
            <span class="pmain-title3 pmain-height" style="width: 100px;">
              {{ testItem.score }}
            </span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      testList: [],
    }
  },

  created() {
    this.fetchTestList()
  },

  methods: {
    fetchTestList() {
      this.$axios.$get('/admin/core/test/userList').then((response) => {
        this.testList = response.data.list
      })
    },
  }
}
</script>
