import { API_BASE_URL } from '../config/api'

/**
 * 构建 AI 恋爱大师 SSE 连接地址
 */
export function buildLoveAppSseUrl(message, chatId) {
  const params = new URLSearchParams({ message, chatId })
  return `${API_BASE_URL}/ai/love_app/chat/sse?${params.toString()}`
}

/**
 * 构建 AI 超级智能体 SSE 连接地址
 */
export function buildManusChatSseUrl(message) {
  const params = new URLSearchParams({ message })
  return `${API_BASE_URL}/ai/manus/chat?${params.toString()}`
}
