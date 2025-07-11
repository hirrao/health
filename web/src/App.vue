<script lang="ts" setup>
import { darkTheme, dateZhCN, zhCN } from 'naive-ui'
const theme = ref(darkTheme)
</script>

<template>
  <n-config-provider :datelocate="dateZhCN" :locate="zhCN" :theme="theme">
    <n-dialog-provider>
      <n-message-provider>
        <router-view v-slot="{ Component }">
          <keep-alive :include="['MainLayout']">
            <component :is="Component" />
          </keep-alive>
        </router-view>
      </n-message-provider>
    </n-dialog-provider>
  </n-config-provider>
</template>
