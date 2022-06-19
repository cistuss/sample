import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { useAuthModule } from "@/store/modules/auth/AuthModule";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    meta: { title: 'TOP' },
    component: () => import("@/pages/common/TopPage.vue")
  },
  {
    path: '/test',
    name: 'test',
    component: () => import("@/pages/test/TestPage.vue")
  },
  {
    path: '/login',
    meta: { title: 'ログイン', isOpen: true },
    component: () => import("@/pages/login/LoginPage.vue")
  },
  {
    path: '/account',
    meta: { title: 'アカウント一覧' },
    component: () => import("@/pages/account/AccountListPage.vue"),
    children: [
      {
        path: 'add',
        meta: { title: 'アカウント新規追加' },
        component: () => import("@/pages/account/AccountAddPage.vue")
      }
    ]
  },
  {
    path: '/:catchAll(.*)',
    name: 'not-found',
    meta: {
      isPublic: true,
      title: '404'
    },
    component: () => import('@/pages/common/NotFoundPage.vue')
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const authModule = useAuthModule()
  if (authModule.loggedIn) {
    if (to.path === '/login') {
      next({path: '/'})
    } else {
      next()
    }
  } else {
    if (to.meta.isOpen) {
      next()
    } else {
      next({path: '/login'})
    }
  }
})

router.afterEach((to) => {
  if (to.meta && to.meta.title) {
    document.title = to.meta.title + ' | Sample'  as string
  } else {
    document.title = 'Sample'
  }
})

export default router
