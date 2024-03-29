import { atom } from 'recoil'
import { DonerType, FundingType, MedicalExpenseType } from '@/types/fundingType'
import { recoilPersist } from 'recoil-persist'

const { persistAtom } = recoilPersist({
  key: 'prevUrl',
  storage: localStorage,
})
export const fundingState = atom<FundingType[]>({
  key: 'fundingState',
  default: [],
})

export const fundingDetailState = atom<FundingType>({
  key: 'fundingDetailState',
  default: {
    fundingNo: 0,
    thumbnailUrl: '',
    title: '',
    birth: '',
    status: '',
    startDate: '',
    endDate: '',
    totalAmount: 0,
    targetAmount: 0,
    createdAt: '',
    content: '',
  },
  effects_UNSTABLE: [persistAtom],
})

export const donerListState = atom<DonerType[]>({
  key: 'donerListState',
  default: [],
})

export const medicalExpenseState = atom<MedicalExpenseType[]>({
  key: 'medicalExpenseState',
  default: [],
})

export const prevUrlState = atom<string>({
  key: 'prevUrlState',
  default: '',
  effects_UNSTABLE: [persistAtom],
})
