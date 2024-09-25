import { navType } from '@/types/navType'
import { atom } from 'recoil'
import { recoilPersist } from 'recoil-persist'

const { persistAtom } = recoilPersist({
  key: 'currentNav',
  storage: sessionStorage,
})

export const currentNavState = atom<navType>({
  key: 'currentNavState',
  default: {
    name: '등록한 펀딩 조회',
    url: '/funding',
    label: '펀딩 목록'
  },
  effects_UNSTABLE: [persistAtom],
})
