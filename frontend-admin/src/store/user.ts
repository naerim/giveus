import { userType } from '@/types/authTypes'
import { atom } from 'recoil'
import { recoilPersist } from 'recoil-persist'

const { persistAtom } = recoilPersist({
  key: 'currentAdmin',
  storage: localStorage,
})

const defaultAdmin = {
  adminNo: -1,
  id: '',
  email: '',
  name: '',
  createdAt: '',
  imageUrl: '/img/img_default_profile.png',
  provider: '',
  snsKey: '',
  ethAddress: '',
}


export const adminState = atom<userType>({
  key: 'adminState',
  default: defaultAdmin,
  effects_UNSTABLE: [persistAtom],
})
