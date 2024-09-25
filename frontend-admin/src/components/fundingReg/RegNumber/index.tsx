import * as r from '@/components/fundingReg/RegNumber/RegNumber.styled'
import { RegInputTypeString } from '@/types/fundingType'

const index = (props: RegInputTypeString) => {
  const { id, placeholder, value, setValue } = props

  const handleBirthChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault()
    setValue(e.target.value)

    //   let strBirth: any = e.target.value.split('')
    //   strBirth.splice(4, 0, '-')
    //   strBirth.splice(2, 0, '-')
    //   if (strBirth[0] < 3) {
    //     strBirth.unshift('20')
    //   } else strBirth.unshift('19')
    //   strBirth = strBirth.join('')

    //   setValue(prevData => ({
    //     ...prevData,
    //     birth: strBirth,
    //   }))
  }

  return (
    <>
      <r.Input
        id={id}
        placeholder={placeholder}
        value={value}
        onChange={e => handleBirthChange(e)}
        maxLength={6}
      />
    </>
  )
}

export default index
