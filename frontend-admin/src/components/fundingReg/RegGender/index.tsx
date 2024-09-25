import React from 'react'
import * as r from '@/components/fundingReg/RegGender/RegGender.styled'
import { RegInputTypeString } from '@/types/fundingType'

const index = (props: RegInputTypeString) => {
  const { value, setValue } = props

  const handleGenderChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault()
    setValue(e.target.value)

    // let strGender = 'F'
    // if (e.target.value === '1' || e.target.value === '3') strGender = 'M'
    // setValue(prevData => ({
    //   ...prevData,
    //   gender: strGender,
    // }))
  }

  return (
    <>
      <r.Minus> - </r.Minus>
      <r.SubWrap>
        <r.GenderInput
          value={value}
          onChange={e => handleGenderChange(e)}
          maxLength={1}
        />
        <r.Star>******</r.Star>
      </r.SubWrap>
    </>
  )
}

export default index
