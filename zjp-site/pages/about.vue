<template>
  <!--关于我们-->
  <div class="bg">
    <div class="contain" id="tabs" style="margin:0 auto; border-radius:14px;">
      <div class="text-nav">
        <ul class="clearfix">
          <li :class="{ selected: $route.fullPath === '/about' }">
            <NuxtLink class="text-link" to="/about">关于我们</NuxtLink>
          </li>          
          <li :class="{ selected: $route.fullPath === '/about/profile' }">
            <NuxtLink class="text-link" to="/about/profile">联系我们</NuxtLink>
          </li>
          <li :class="{ selected: $route.fullPath === '/about/leader' }">
            <NuxtLink class="text-link" to="/about/leader">管理团队</NuxtLink>
          </li>
          <li :class="{ selected: $route.fullPath === '/about/notice' }">
            <NuxtLink class="text-link" to="/about/notice">免责声明</NuxtLink>
          </li>          
          <li :class="{ selected: $route.fullPath === '/about/job' }">
            <NuxtLink class="text-link" to="/about/job">加入我们</NuxtLink>
          </li>
        </ul>
      </div>
      <NuxtChild />
    </div>
  </div>
</template>

<script>
import '~/assets/css/about.css'

export default {}
</script>
