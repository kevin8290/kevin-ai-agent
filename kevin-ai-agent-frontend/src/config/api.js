/** 当前 API 基础地址 */
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || '/api'

/** 当前 API 环境：local | production */
export const API_ENV = import.meta.env.VITE_APP_API_ENV || 'local'

export const isLocalApi = API_ENV === 'local'
