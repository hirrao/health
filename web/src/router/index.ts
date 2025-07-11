import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'

const router = createRouter({
  history: import.meta.env.DEV
    ? createWebHistory(import.meta.env.BASE_URL)
    : createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/*',
      component: () => import('@/pages/MainLayout.vue'),
      children: [
        {
          path: '/',
          component: () => import('@/pages/Home.vue'),
        },
        {
          path: '/test',
          component: () => import('@/pages/Test.vue'),
        },
      ],
    },
  ],
})

export default router
