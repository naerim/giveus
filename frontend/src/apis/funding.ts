import { authRequest, publicRequest } from '@utils/requestMethods'

const url = '/api/v1/funding'

// 펀딩 목록 조회
export const fetchFundingList = async () => {
  return authRequest.get(`${url}`).then(res => res.data.data)
}

// 펀딩 상세 조회
export const fetchFundingDetail = async (id: number) => {
  return authRequest.get(`${url}/${id}`).then(res => res.data.data)
}

// 펀딩 상세 - 기부자 명단 조회
export const fetchFundingParticipants = async (id: number) => {
  return authRequest.get(`${url}/${id}/participants`).then(res => res.data.data)
}

// 펀딩 상세 - 기금 사용 내역 조회
export const fetchFundingUsage = async (id: number) => {
  return authRequest.get(`${url}/${id}/usage`).then(res => res.data.data)
}

// 펀딩 2차 등록
export const createSecondReg = async (data: FormData) => {
  return publicRequest
    .post(`${url}`, data, {
      headers: { 'Content-Type': 'multipart/form-data' },
    })
    .then(res => res.data)
}

// 펀딩명 검색
export const searchFunding = async (query: string) => {
  return publicRequest
    .get(`${url}/search?query=${query}`)
    .then(res => res.data.data)
    .catch(err => err)
}

// 최근 후원 참여자 내역 조회
export const fetchRecentParticipants = async () => {
  return publicRequest
    .get(`${url}/participants?limit=3`)
    .then(res => {
      console.log(res)
      return res.data
    })
    .catch(err => err)
}

// 누적 기부 금액 조회
export const fetchTotalDonateAmount = async () => {
  return publicRequest
    .get(`${url}/total-amount`)
    .then(res => res.data.data)
    .catch(err => err)
}

// 종료일이 얼마 남지 않은 펀딩 전체 목록 조회
export const fetchSoonOver = async () => {
  return publicRequest
    .get(`${url}/soon-over?limit=3`)
    .then(res => res.data.data)
    .catch(err => err)
}

// 회원 펀딩 참여 내역 조회
export const fetchUserDonations = async (memberNo: number) => {
  return authRequest
    .get(`${url}/mypage/${memberNo}`)
    .then(res => res.data.data)
    .catch(err => err)
}
