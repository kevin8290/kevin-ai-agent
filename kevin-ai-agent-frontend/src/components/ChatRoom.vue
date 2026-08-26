<script setup>
import { nextTick, ref, watch } from 'vue'

const props = defineProps({
  title: {
    type: String,
    required: true,
  },
  subtitle: {
    type: String,
    default: '',
  },
  chatId: {
    type: String,
    default: '',
  },
  messages: {
    type: Array,
    default: () => [],
  },
  loading: {
    type: Boolean,
    default: false,
  },
})

const emit = defineEmits(['send'])

const inputText = ref('')
const messageListRef = ref(null)

function handleSend() {
  const text = inputText.value.trim()
  if (!text || props.loading) return
  emit('send', text)
  inputText.value = ''
}

function handleKeydown(event) {
  if (event.key === 'Enter' && !event.shiftKey) {
    event.preventDefault()
    handleSend()
  }
}

function scrollToBottom() {
  nextTick(() => {
    if (messageListRef.value) {
      messageListRef.value.scrollTop = messageListRef.value.scrollHeight
    }
  })
}

watch(
  () => props.messages,
  () => scrollToBottom(),
  { deep: true }
)
</script>

<template>
  <div class="chat-room">
    <div class="chat-bg" />

    <header class="chat-header">
      <div class="header-inner">
        <router-link to="/" class="back-btn">← 返回主页</router-link>
        <div class="header-info">
          <h1>{{ title }}</h1>
          <p v-if="subtitle">{{ subtitle }}</p>
        </div>
        <p v-if="chatId" class="chat-id">{{ chatId.slice(0, 8) }}...</p>
      </div>
    </header>

    <main ref="messageListRef" class="message-list">
      <div class="message-container">
        <div
          v-for="message in messages"
          :key="message.id"
          class="message-row"
          :class="message.role"
        >
          <div class="avatar">{{ message.role === 'user' ? '我' : 'AI' }}</div>
          <div class="bubble">
            <p class="content">{{ message.content }}</p>
            <span v-if="message.streaming" class="typing-indicator">▍</span>
          </div>
        </div>

        <div v-if="messages.length === 0" class="empty-tip">
          <p class="empty-title">开始一段新对话</p>
          <p class="empty-desc">发送一条消息，AI 将实时为你回复</p>
        </div>
      </div>
    </main>

    <footer class="chat-input-wrap">
      <div class="chat-input">
        <textarea
          v-model="inputText"
          :disabled="loading"
          placeholder="输入消息，Enter 发送，Shift + Enter 换行"
          rows="3"
          @keydown="handleKeydown"
        />
        <button type="button" :disabled="loading || !inputText.trim()" @click="handleSend">
          {{ loading ? '回复中...' : '发送' }}
        </button>
      </div>
    </footer>
  </div>
</template>

<style scoped>
.chat-room {
  position: relative;
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: hidden;
}

.chat-bg {
  position: absolute;
  inset: 0;
  background:
    radial-gradient(circle at 20% 0%, rgba(99, 102, 241, 0.1), transparent 36%),
    radial-gradient(circle at 80% 100%, rgba(236, 72, 153, 0.08), transparent 32%),
    linear-gradient(180deg, #f8fafc 0%, #f1f5f9 100%);
  z-index: 0;
}

.chat-header,
.message-list,
.chat-input-wrap {
  position: relative;
  z-index: 1;
}

.chat-header {
  padding: 24px 32px;
  background: rgba(255, 255, 255, 0.72);
  border-bottom: 1px solid var(--color-border);
  backdrop-filter: blur(16px);
}

.header-inner {
  max-width: var(--chat-width);
  margin: 0 auto;
  display: grid;
  grid-template-columns: auto 1fr auto;
  align-items: center;
  gap: 24px;
}

.back-btn {
  color: var(--color-primary);
  text-decoration: none;
  font-size: 15px;
  font-weight: 500;
  white-space: nowrap;
  padding: 10px 16px;
  border-radius: 999px;
  background: var(--color-primary-soft);
  transition: background 0.2s ease;
}

.back-btn:hover {
  background: #e0e7ff;
}

.header-info h1 {
  margin: 0;
  font-size: 28px;
  font-weight: 700;
  letter-spacing: -0.02em;
}

.header-info p {
  margin: 6px 0 0;
  font-size: 15px;
  color: var(--color-text-muted);
}

.chat-id {
  margin: 0;
  padding: 8px 14px;
  border-radius: 999px;
  background: rgba(15, 23, 42, 0.04);
  color: var(--color-text-muted);
  font-family: Consolas, monospace;
  font-size: 12px;
}

.message-list {
  flex: 1;
  overflow-y: auto;
  padding: 40px 32px 24px;
}

.message-container {
  max-width: var(--chat-width);
  margin: 0 auto;
}

.message-row {
  display: flex;
  align-items: flex-start;
  gap: 16px;
  margin-bottom: 28px;
}

.message-row.user {
  flex-direction: row-reverse;
}

.avatar {
  width: 44px;
  height: 44px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  font-weight: 700;
  flex-shrink: 0;
  box-shadow: var(--shadow-sm);
}

.message-row.ai .avatar {
  background: linear-gradient(135deg, #dbeafe, #bfdbfe);
  color: #1d4ed8;
}

.message-row.user .avatar {
  background: linear-gradient(135deg, #4f46e5, #6366f1);
  color: #ffffff;
}

.bubble {
  max-width: min(78%, 760px);
  padding: 18px 22px;
  border-radius: 22px;
  line-height: 1.75;
  font-size: 15px;
  word-break: break-word;
  white-space: pre-wrap;
  box-shadow: var(--shadow-sm);
}

.message-row.ai .bubble {
  background: rgba(255, 255, 255, 0.92);
  color: #1e293b;
  border: 1px solid rgba(255, 255, 255, 0.8);
  border-top-left-radius: 8px;
}

.message-row.user .bubble {
  background: linear-gradient(135deg, #4f46e5, #6366f1);
  color: #ffffff;
  border-top-right-radius: 8px;
}

.content {
  margin: 0;
}

.typing-indicator {
  display: inline-block;
  animation: blink 1s step-end infinite;
  color: var(--color-primary);
}

@keyframes blink {
  50% {
    opacity: 0;
  }
}

.empty-tip {
  margin-top: 120px;
  text-align: center;
}

.empty-title {
  margin: 0 0 12px;
  font-size: 24px;
  font-weight: 700;
  color: var(--color-text);
}

.empty-desc {
  margin: 0;
  font-size: 16px;
  color: var(--color-text-muted);
}

.chat-input-wrap {
  padding: 0 32px 32px;
}

.chat-input {
  max-width: var(--chat-width);
  margin: 0 auto;
  display: flex;
  gap: 16px;
  padding: 20px;
  border-radius: var(--radius-lg);
  background: rgba(255, 255, 255, 0.88);
  border: 1px solid rgba(255, 255, 255, 0.9);
  box-shadow: var(--shadow-md);
  backdrop-filter: blur(16px);
}

.chat-input textarea {
  flex: 1;
  resize: none;
  border: 1px solid var(--color-border);
  border-radius: 16px;
  padding: 16px 18px;
  font-size: 15px;
  line-height: 1.6;
  background: #ffffff;
  outline: none;
  transition: border-color 0.2s ease, box-shadow 0.2s ease;
}

.chat-input textarea:focus {
  border-color: #818cf8;
  box-shadow: 0 0 0 4px rgba(99, 102, 241, 0.12);
}

.chat-input button {
  align-self: flex-end;
  min-width: 112px;
  height: 52px;
  border: none;
  border-radius: 16px;
  background: linear-gradient(135deg, #4f46e5, #6366f1);
  color: #ffffff;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  box-shadow: 0 12px 24px rgba(79, 70, 229, 0.24);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.chat-input button:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 16px 28px rgba(79, 70, 229, 0.28);
}

.chat-input button:disabled {
  opacity: 0.65;
  cursor: not-allowed;
  box-shadow: none;
}
</style>
