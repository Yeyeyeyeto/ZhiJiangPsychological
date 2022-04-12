<template>
  <ul>
    <!-- 账号信息（基本信息、更新头像、修改密码）、系统通知、消息私信、咨询管理、文章管理 -->

    <li :class="{ 'pleft-cur': $route.fullPath === '/user/consultant' }">
      <span>
        <NuxtLink to="/user/consultant"> <i class="dot dot1"></i>账号信息</NuxtLink>
      </span>
    </li>
    <!-- <li :class="{ 'pleft-cur': $route.fullPath === '/user/fund' }">
      <span>
        <NuxtLink style="text-align:center;width:115px;" to="/user/fund">
          咨询计划
        </NuxtLink>
      </span>
    </li> -->
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/consultmanagement' }">
      <span>
        <NuxtLink to="/user/consultmanagement"> <i class="dot dot05"></i>咨询管理</NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/articlemanagement' }">
      <span>
        <NuxtLink to="/user/articlemanagement"> <i class="dot dot03"></i>文章管理</NuxtLink>
      </span>
    </li>
    <li :class="{ 'pleft-cur': $route.fullPath === '/user/alerts' }">
      <span>
        <NuxtLink to="/user/alerts"> <i class="dot dot08"></i>消息通知</NuxtLink>
      </span>
    </li>

  </ul>
</template>

<script>
export default {}
</script>