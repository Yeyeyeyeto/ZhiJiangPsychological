<template>
  <div class="personal-main">
    <div class="personal-money">
      <h3><i>预约咨询</i></h3>

      <div class="personal-moneylist" style="margin-top: 40px;">
        <div class="pmain-contitle">
          <span class="pmain-title1 fb" style="width: 100px;">
            用户ID
          </span>
          <span class="pmain-title2 fb" style="width: 90px;">
            订单金额
          </span>
          <span class="pmain-title3 fb" style="width: 150px;">
             下单时间
          </span>
          <span class="pmain-title3 fb" style="width: 160px;">
             最新操作时间
          </span>
          <span class="pmain-title3 fb" style="width: 160px;">
             订单状态
          </span>
          <span class="pmain-title3 fb" style="width: 140px;">
             操作
          </span>
        </div>
        <ul>
          <li
            v-for="orderItem in orderList"
            :key="orderItem.id"
            style="width:100%"
          >
            <span class="pmain-title1 pmain-height" style="width: 100px;">
              {{ orderItem.userId }}
            </span>
            <span class="pmain-title2 pmain-height" style="width: 90px;">
              {{ orderItem.amount }}
            </span>
            <span class="pmain-title3 pmain-height" style="width: 150px;">
              {{ orderItem.createTime }}
            </span>
            <span class="pmain-title3 pmain-height" style="width: 160px;">
              {{ orderItem.updateTime }}
            </span>
            <span class="pmain-title3 pmain-height" style="width: 160px;">
              <span v-if="orderItem.status === 0">待咨询师确认</span>
              <span v-if="orderItem.status === 1">进行中</span>
              <span v-if="orderItem.status === 2">已完成</span>
              <span v-if="orderItem.status === 3">已取消</span>
            </span>
            <span class="pmain-title3 pmain-height" style="width: 160px;">
              <span v-if="orderItem.status === 0"><button class="action-btn" @click="startOrder(orderItem.id)" >确认订单</button></span>              
              <span v-if="orderItem.status === 0"><button class="action-btn" @click="cancelOrder(orderItem.id)">取消订单</button></span>
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
      orderList: [],
    }
  },

  created() {
    this.fetchOrderList()
  },

  methods: {
    fetchOrderList() {
      this.$axios.$get('/admin/core/order/consultantList').then((response) => {
        this.orderList = response.data.list
      })
    },
    cancelOrder(id) {
      this.$axios.$get('/admin/core/order/cancelOrder/' + id).then((response) => {
        location.reload();
      })
    },
    startOrder(id) {
      this.$axios.$get('/admin/core/order/startOrder/' + id).then((response) => {
        location.reload();
      })
    },
  }
}
</script>
