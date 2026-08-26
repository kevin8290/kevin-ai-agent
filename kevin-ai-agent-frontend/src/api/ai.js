import request from './request'

/**
 * 构建 AI 恋爱大师 SSE 连接地址
 */
export function buildLoveAppSseUrl(message, chatId) {
  const params = new URLSearchParams({ message, chatId })
  return `${request.defaults.baseURL}/ai/love_app/chat/sse?${params.toString()}`
}

/**
 * 构建 AI 超级智能体 SSE 连接地址
 */
export function buildManusChatSseUrl(message) {
  const params = new URLSearchParams({ message })
  return `${request.defaults.baseURL}/ai/manus/chat?${params.toString()}`
}
