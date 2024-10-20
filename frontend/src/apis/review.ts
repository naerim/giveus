import { authRequest, publicRequest } from '@/utils/requestMethods'
import { HomeReviewType } from '@/types/reviewType'

const url = '/api/v1/review'

// 펀딩 후기 전체 조회
export const fetchReviewAll = async () =>
  publicRequest
    .get<HomeReviewType[]>(`${url}/findAll/3`)
    .then(res => res.data)
    .catch(err => err)

// 펀딩 후기 상세 조회
export const fetchReview = async (fundingNo: number) =>
  authRequest
    .get(`${url}/${fundingNo}`)
    .then(res => res.data.data)
    .catch(err => err)
