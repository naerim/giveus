import axios from 'axios'

//
export const BASE_URL = 'https://admin.giveus.site'
// export const BASE_URL = 'http://localhost:8081'

export const publicRequest = axios.create({
  baseURL: BASE_URL,
})

export const authRequest = axios.create({
  baseURL: BASE_URL,
})

export const reviewRequest = axios.create({
  baseURL: 'https://giveus.site',
})
