import { FundingType, RecentParticipantType } from '@/types/fundingType'
import { HomeReviewType } from '@/types/reviewType'

// 실시간 기부 참여자들
export const contributors: RecentParticipantType[] = [
  {
    memberFundingNo: 0,
    name: '아몬드',
    amount: 50000,
    createdAt: '2024-10-20',
    isPublic: false,
    imageUrl: '/img/img_default_profile.png',
  },
  {
    memberFundingNo: 1,
    name: '캐슈넛',
    amount: 10000,
    createdAt: '2024-10-19',
    isPublic: false,
    imageUrl: '/img/img_default_profile.png',
  },
  {
    memberFundingNo: 2,
    name: '피스타치오',
    amount: 10000,
    createdAt: '2024-10-10',
    isPublic: false,
    imageUrl: '/img/img_default_profile.png',
  },
]

// 기부 후기
export const homeReviews: HomeReviewType[] = [
  {
    memberFundingCount: 5,
    fundingNo: 100,
    targetAmount: 200000,
    reviewNo: 0,
    imageUrl: '',
    title: '기부 1',
    existUsageHistory: true,
  },
  {
    memberFundingCount: 5,
    fundingNo: 101,
    targetAmount: 500000,
    reviewNo: 1,
    imageUrl: '',
    title: '기부 2',
    existUsageHistory: false,
  },
  {
    memberFundingCount: 5,
    fundingNo: 102,
    targetAmount: 500000,
    reviewNo: 2,
    imageUrl: '',
    title: '기부 3',
    existUsageHistory: false,
  },
]

// 곧 끝나는 펀딩
export const soonOverFundings: FundingType[] = [
  {
    fundingNo: 200,
    thumbnailUrl: '/img/img_funding1.png',
    title: '폐아암 2기 8세(남) 펀딩',
    birth: '2020-10-25',
    status: '',
    startDate: '2024-05-29',
    endDate: '2024-12-25',
    totalAmount: 300000,
    targetAmount: 400000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 10,
    isRegReview: true,
  },
  {
    fundingNo: 201,
    thumbnailUrl: '/img/img_funding1.png',
    title: '소아암 2기 5세(여) 펀딩',
    birth: '2020-10-25',
    status: '',
    startDate: '2024-05-29',
    endDate: '2024-12-25',
    totalAmount: 500000,
    targetAmount: 500000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 15,
    isRegReview: true,
  },
  {
    fundingNo: 202,
    thumbnailUrl: '/img/img_funding1.png',
    title: '폐아암 2기 8세(남) 펀딩',
    birth: '2020-10-25',
    status: '',
    startDate: '2024-05-29',
    endDate: '2025-01-12',
    totalAmount: 140000,
    targetAmount: 400000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 10,
    isRegReview: true,
  },
]

// 기부
export const fundings: FundingType[] = [
  {
    fundingNo: 200,
    thumbnailUrl: '/img/img_funding1.png',
    title: '소아암 1기 5세(여) 펀딩',
    birth: '2020-10-25',
    status: '진행중',
    startDate: '2024-05-29',
    endDate: '2024-12-25',
    totalAmount: 100000,
    targetAmount: 400000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 10,
    isRegReview: true,
  },
  {
    fundingNo: 200,
    thumbnailUrl: '/img/img_funding1.png',
    title: '폐아암 2기 8세(남) 펀딩',
    birth: '2020-10-25',
    status: '진행중',
    startDate: '2024-05-29',
    endDate: '2024-12-25',
    totalAmount: 300000,
    targetAmount: 400000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 10,
    isRegReview: true,
  },
  {
    fundingNo: 201,
    thumbnailUrl: '/img/img_funding1.png',
    title: '소아암 2기 5세(여) 펀딩',
    birth: '2020-10-25',
    status: '진행중',
    startDate: '2024-05-29',
    endDate: '2024-12-25',
    totalAmount: 500000,
    targetAmount: 500000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 15,
    isRegReview: true,
  },
  {
    fundingNo: 202,
    thumbnailUrl: '/img/img_funding1.png',
    title: '폐아암 2기 8세(남) 펀딩',
    birth: '2020-10-25',
    status: '진행중',
    startDate: '2024-05-29',
    endDate: '2025-01-12',
    totalAmount: 140000,
    targetAmount: 400000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 10,
    isRegReview: true,
  },
  {
    fundingNo: 502,
    thumbnailUrl: '/img/img_funding1.png',
    title: '폐아암 2기 8세(남) 펀딩',
    birth: '2020-10-25',
    status: '진행완료',
    startDate: '2024-05-29',
    endDate: '2023-01-12',
    totalAmount: 140000,
    targetAmount: 140000,
    createdAt: '2024-05-29',
    content: '소아암',
    donationCnt: 200,
    isRegReview: true,
  },
]
