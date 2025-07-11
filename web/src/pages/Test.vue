<template>
  <n-card title="测试页面">
    <n-button @click="showMessage">测试按钮</n-button>
  </n-card>
</template>

<script lang="ts" setup>
import { Locator } from '@/data'

const test = Locator.TestRepository
const message = useMessage()
const showMessage = async () => {
  const resp = await test.test()
  message.success(await resp.text())
}
</script>
