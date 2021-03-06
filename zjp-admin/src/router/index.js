import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/core/questionnaire',
    component: Layout,
    redirect: '/core/questionnaire/list',
    name: 'coreQuestionnaire',
    meta: { title: '问卷管理', icon: 'el-icon-s-marketing' },
    // false（默认值）当且仅当父节点下只有一个子节点时，不显示父节点
    // true 任何时候都显示父节点和子节点
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: 'coreQuestionnaireList',  // 每个路由节点的name的名字不能相同
        component: () => import('@/views/core/questionnaire/list'),  // 指向template模版组件
        meta: { title: '问卷列表' } // 定义导航的标题
      },
      {
        path: 'create',
        name: 'coreQuestionnaireCreate',
        component: () => import('@/views/core/questionnaire/form'),
        meta: { title: '新增问卷' }
      },
      {
        path: 'edit/:id', // :id是一个占位符，表示这部分url会是任何一个id
        name: 'coreQuestionnaireEdit',
        component: () => import('@/views/core/questionnaire/form'),
        meta: { title: '编辑问卷' },
        hidden: true
      },
      {
        path: 'detail/:id', // :id是一个占位符，表示这部分url会是任何一个id
        name: 'coreQuestionnaireDetail',
        component: () => import('@/views/core/questionnaire/detail'),
        meta: { title: '编辑问卷详情' },
        hidden: true
      },
      {
        path: 'show/:id', // :id是一个占位符，表示这部分url会是任何一个id
        name: 'coreQuestionnaireShow',
        component: () => import('@/views/core/questionnaire/show'),
        meta: { title: '查看问卷详情' },
        hidden: true
      }
    ]
  },

    {
    path: '/core/article',
    component: Layout,
    redirect: '/core/article/list',
    name: 'coreArticle',
    meta: { title: '文章管理', icon: 'el-icon-s-marketing' },
    // false（默认值）当且仅当父节点下只有一个子节点时，不显示父节点
    // true 任何时候都显示父节点和子节点
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: 'coreArticleList',  // 每个路由节点的name的名字不能相同
        component: () => import('@/views/core/article/list'),  // 指向template模版组件
        meta: { title: '文章列表' } // 定义导航的标题
      },
      {
        path: 'create',
        name: 'coreArticleCreate',
        component: () => import('@/views/core/article/form'),
        meta: { title: '新增文章' }
      },
      {
        path: 'edit/:id', // :id是一个占位符，表示这部分url会是任何一个id
        name: 'coreArticleEdit',
        component: () => import('@/views/core/article/form'),
        meta: { title: '编辑问卷' },
        hidden: true
      },
    ]
  },

  {
    path: '/core/user',
    component: Layout,
    redirect: '/core/user/list',
    name: 'coreUser',
    meta: { title: '用户管理', icon: 'user' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: 'coreUserList',
        component: () => import('@/views/core/user/list'),
        meta: { title: '用户列表' }
      },
      {
        path: 'test',
        name: 'coreUserTest',
        component: () => import('@/views/core/user/test'),
        meta: { title: '用户测试记录' }
      },
    ]
  },

  {
    path: '/core/consultant',
    component: Layout,
    redirect: '/core/consultant/list',
    name: 'coreConsultant',
    meta: { title: '咨询师管理', icon: 'user' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: 'coreConsultantList',
        component: () => import('@/views/core/consultant/list'),
        meta: { title: '咨询师列表' }
      },
      {
        path: 'detail/:id',
        name: 'coreConsultantDetail',
        component: () => import('@/views/core/consultant/detail'),
        meta: { title: '咨询师详细信息' },
        hidden: true
      },
    ]
  },

    {
    path: '/core/order',
    component: Layout,
    redirect: '/core/order/list',
    name: 'coreOrder',
    meta: { title: '订单管理', icon: 'user' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: 'coreOrderList',
        component: () => import('@/views/core/order/list'),
        meta: { title: '订单列表' }
      },
      // {
      //   path: 'detail/:id',
      //   name: 'coreorderDetail',
      //   component: () => import('@/views/core/order/detail'),
      //   meta: { title: '咨询师详细信息' },
      //   hidden: true
      // },
    ]
  },

  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'el-icon-s-help' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       name: 'Menu2',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
