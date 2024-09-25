import * as r from '@/components/fundingReg/RegInput/RegInput.styled'
import { RegInputType } from '@/types/fundingType'
import { useState } from 'react'

const index = (props: RegInputType) => {
  const { id, label, placeholder, value, setValue } = props
  const [newValue, setNewValue] = useState(value)

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const updatedValue =
      id === 'targetAmount' ? Number(e.target.value) : e.target.value
    e.preventDefault()
    setNewValue(e.target.value)
    setValue(prevData => ({
      ...prevData,
      [id]: updatedValue,
    }))
  }

  return (
    <r.Container>
      <r.Label htmlFor={id}>{label}</r.Label>
      <r.Input
        id={id}
        placeholder={placeholder}
        value={newValue}
        onChange={handleChange}
        readOnly={id === 'startDate'}
        $isStartDate={id === 'startDate'}
      />
    </r.Container>
  )
}

export default index
