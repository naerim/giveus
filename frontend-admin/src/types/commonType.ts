import { Dispatch, SetStateAction } from 'react'

export type FullButtonType = {
  text: string
  disabled: boolean
  onClick?: () => void
}

export type BooleanStateType = {
  value: boolean
  setValue: Dispatch<SetStateAction<boolean>>
}

export type ModalType = {
  name: string
  children: React.ReactNode
  onClose: () => void
}

export type StringStateType = {
  value: string
  setValue: Dispatch<SetStateAction<string>>
}

export type FileStateType = {
  value: File | null
  setValue: Dispatch<SetStateAction<File | null>>
}