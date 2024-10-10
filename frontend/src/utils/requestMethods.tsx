import axios from 'axios'

// export const BASE_URL = 'http://localhost:8081'
export const BASE_URL = 'https://giveus.site'

export const publicRequest = axios.create({
  baseURL: 'http://localhost:5173',
})

export const authRequest = axios.create({
  baseURL: BASE_URL,
})
