<script setup>
import { onMounted, onUnmounted, ref } from 'vue'
import { buildLoveAppSseUrl } from '../api/ai'
import ChatRoom from '../components/ChatRoom.vue'
import { generateChatId } from '../utils/chatId'
import { connectSse } from '../utils/sse'

const chatId = ref('')
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
  closeSse = connectSse(buildLoveAppSseUrl(text, chatId.value), {
    onMessage: (chunk) => {
      messages.value[aiMessageIndex].content += chunk
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

onMounted(() => {
  chatId.value = generateChatId()
})

onUnmounted(() => {
  closeSse?.()
})
</script>

<template>
  <ChatRoom
    title="AI 恋爱大师"
    subtitle="恋爱心理专家，倾听你的情感困惑"
    :chat-id="chatId"
    :messages="messages"
    :loading="loading"
    @send="handleSend"
  />
</template>
