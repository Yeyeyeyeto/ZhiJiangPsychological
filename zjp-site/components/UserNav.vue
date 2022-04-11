<template>
  <ul>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user' }">
      <span>
        <NuxtLink to="/user"> <i class="dot dot1"></i>账号信息</NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/fund' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/fund">
          咨询计划
        </NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user">
          测试记录
        </NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user">
          咨询记录
        </NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/recharge' }">
      <span
        ><NuxtLink to="/user/recharge"
          ><i class="dot dot03"></i>消息中心</NuxtLink
        ></span
      >
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user">
          系统通知
        </NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user">
          消息私信
        </NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/withdraw' }">
      <span
        ><NuxtLink to="/user/withdraw"
          ><i class="dot dot04"></i>账户安全</NuxtLink
        ></span
      >
    </li>
  </ul>
</template>

<script>
export default {}
</script>
