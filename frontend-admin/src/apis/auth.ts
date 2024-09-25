// import { JoinUserType } from '@/types/reqType'
import { authRequest, publicRequest } from '@utils/requestMethods'

const url = '/api/v1/admin'

// 5분에 한 번 저장
const JWT_EXPIRY_TIME = 3600 * 1000

// refresh token 요청
export const refresh = async () => {
  return authRequest
    .post(`${url}/refresh`)
    .then(res => loginSuccess(res.data))
    .catch(err => console.log(err))
}

// 로그인 성공 시
export const loginSuccess = async (res: { accessToken: string }) => {
  const { accessToken } = res

  authRequest.defaults.headers.Authorization = `Bearer ${accessToken}`
  setTimeout(() => refresh(), JWT_EXPIRY_TIME - 5000)
}

// 회원가입
export const joinUser = async (req: { name: string, address: string }) => {
  const storage = localStorage.getItem('register')
  const email = storage && JSON.parse(storage).email
  const provider = storage && JSON.parse(storage).provider

  return publicRequest
    .put(`${url}/join`, {
      name: req.name,
      ethAddress :req.address,
      email: email || '',
      provider: provider || '',
    })
    .then(res => res)
}

// 회원 정보 조회
export const fetchUserInfo = async () => {
  return authRequest.get(`${url}/info`).then(res => res)
}
