import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router'

const router = createRouter({
  history: import.meta.env.DEV
    ? createWebHistory(import.meta.env.BASE_URL)
    : createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/*',
      component: () => import('@/pages/mainLayout/MainLayout.vue'),
      children: [
        {
          path: '/',
          component: () => import('@/pages/home/Home.vue'),
        },
        {
          path: '/test',
          component: () => import('@/pages/test/Test.vue'),
        },
      ],
    },
  ],
})

export default router
