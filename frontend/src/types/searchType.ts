import { StringStateType } from '@/types/commonType'

export type SearchFormType = {
  onSearch: (e: React.KeyboardEvent) => void
  resetKeyword: () => void
} & StringStateType
