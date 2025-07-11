<script lang="ts" setup>
import type { MenuOption } from 'naive-ui'
import { HomeOutlined, AccessTimeOutlined } from '@vicons/material'
import { NIcon } from 'naive-ui'
import { RouterLink } from 'vue-router'
const route = useRoute()
const menuCollapsed = ref(false)
const hasSider = ref(true)

const renderLabel = (text: string, href: string) => () =>
  h(RouterLink, { to: href }, { default: () => text })
const renderIcon = (icon: Component) => () => h(NIcon, null, { default: () => h(icon) })

const menuOptions = computed<MenuOption[]>(() => [
  {
    label: renderLabel('首页', '/'),
    icon: renderIcon(HomeOutlined),
    key: 'home',
  },
  {
    label: renderLabel('测试', '/test'),
    icon: renderIcon(AccessTimeOutlined),
    key: 'test',
  },
])

const menuKey = computed(() => route.path)
</script>

<template>
  <n-layout :has-sider="hasSider" style="width: 100%; min-height: 100vh">
    <n-layout-sider
      collapse-mode="width"
      :collapsed="menuCollapsed"
      :collapsed-width="64"
      show-trigger="arrow-circle"
      style="height: 100vh"
      :width="240"
      @collapse="menuCollapsed = true"
      @expand="menuCollapsed = false"
    >
      <n-scrollbar style="max-height: 120px">
        <n-menu
          :collapsed="menuCollapsed"
          :collapsed-width="64"
          :options="menuOptions"
          :value="menuKey"
          :width="menuCollapsed ? 64 : 240"
        />
      </n-scrollbar>
    </n-layout-sider>
    <n-layout-content class="layout-content">
      <router-view v-slot="{ Component }">
        <keep-alive :include="['*']">
          <component :is="Component" />
        </keep-alive>
      </router-view>
    </n-layout-content>
  </n-layout>
</template>

<style>
.layout-content {
  max-width: 1000px;
  margin: 0 auto;
  padding: 30px;
  transition: margin-left 0.3s;
}
</style>
