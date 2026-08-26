/**
 * 通过 EventSource 建立 SSE 连接并流式接收消息
 * @param {string} url SSE 地址
 * @param {object} callbacks 回调函数
 * @returns {() => void} 关闭连接的函数
 */
export function connectSse(url, { onMessage, onError, onComplete } = {}) {
  const eventSource = new EventSource(url)
  let finished = false

  const finish = (isError = false) => {
    if (finished) return
    finished = true
    eventSource.close()
    if (isError) {
      onError?.()
    }
    onComplete?.()
  }

  eventSource.onmessage = (event) => {
    if (event.data) {
      onMessage?.(event.data)
    }
  }

  eventSource.onerror = () => {
    // readyState=0 表示连接失败；=2 表示正常结束
    const connectFailed = eventSource.readyState === EventSource.CONNECTING
    finish(connectFailed)
  }

  return () => finish(false)
}
