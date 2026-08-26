<script setup>
import { onUnmounted, ref } from 'vue'
import { buildManusChatSseUrl } from '../api/ai'
import ChatRoom from '../components/ChatRoom.vue'
import { connectSse } from '../utils/sse'

const messages = ref([])
const loading = ref(false)

let closeSse = null
let messageId = 0

function createMessage(role, content, streaming = false) {
  messageId += 1
  return { id: messageId, role, content, streaming }
}

function handleSend(text) {
  if (loading.value) return

  messages.value.push(createMessage('user', text))

  const aiMessageIndex = messages.value.length
  messages.value.push(createMessage('ai', '', true))
  loading.value = true

  closeSse?.()
  closeSse = connectSse(buildManusChatSseUrl(text), {
    onMessage: (chunk) => {
      const current = messages.value[aiMessageIndex].content
      messages.value[aiMessageIndex].content = current
        ? `${current}\n\n${chunk}`
        : chunk
    },
    onError: () => {
      if (!messages.value[aiMessageIndex].content) {
        messages.value[aiMessageIndex].content = '连接失败，请确认后端服务已启动（端口 8125）。'
      }
    },
    onComplete: () => {
      messages.value[aiMessageIndex].streaming = false
      loading.value = false
      closeSse = null
    },
  })
}

onUnmounted(() => {
  closeSse?.()
})
</script>

<template>
  <ChatRoom
    title="AI 超级智能体"
    subtitle="YuManus — 具备工具调用与多步骤规划能力"
    :messages="messages"
    :loading="loading"
    @send="handleSend"
  />
</template>
