<template>
  <ul>
    <!-- 账号信息（基本信息、更新头像、修改密码）、系统通知、消息私信、预约订单、测试记录 -->

    <li :class="{ 'pleft-cur': $route.fullPath === '/user' }">
      <span>
        <NuxtLink to="/user"> <i class="dot dot1"></i>账号信息</NuxtLink>
      </span>
    </li>
    <!-- <li :class="{ 'pleft-cur': $route.fullPath === '/user/fund' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/fund">
          咨询计划
        </NuxtLink>
      </span>
    </li> -->

    <li :class="{ 'pleft-cur': $route.fullPath === '/user/test' }">
      <span>
        <NuxtLink to="/user/test"> <i class="dot dot05"></i>测试记录</NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/consulting' }">
      <span>
        <NuxtLink to="/user/consulting"> <i class="dot dot07"></i>预约咨询</NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/alerts' }">
      <span>
        <NuxtLink to="/user/alerts"> <i class="dot dot08"></i>消息通知</NuxtLink>
      </span>
    </li>


    <li :class="{ 'pleft-cur': $route.fullPath === '/user/apply' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/apply">
          apply
        </NuxtLink>
      </span>
    </li>
        <li :class="{ 'pleft-cur': $route.fullPath === '/user/bind' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/bind">
          bind
        </NuxtLink>
      </span>
    </li>
        <li :class="{ 'pleft-cur': $route.fullPath === '/user/borrower' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/borrower">
          borrower
        </NuxtLink>
      </span>
    </li>
        <li :class="{ 'pleft-cur': $route.fullPath === '/user/fund' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/fund">
          fund
        </NuxtLink>
      </span>
    </li>
        <li :class="{ 'pleft-cur': $route.fullPath === '/user/recharge' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/recharge">
          recharge
        </NuxtLink>
      </span>
    </li>
        <li :class="{ 'pleft-cur': $route.fullPath === '/user/withdraw' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/withdraw">
          withdraw
        </NuxtLink>
      </span>
    </li>



  </ul>
</template>

<script>
export default {}
</script>
