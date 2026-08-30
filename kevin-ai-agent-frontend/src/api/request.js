import axios from 'axios'
import { API_BASE_URL } from '../config/api'

const request = axios.create({
  baseURL: API_BASE_URL,
  timeout: 300000,
})

export default request
