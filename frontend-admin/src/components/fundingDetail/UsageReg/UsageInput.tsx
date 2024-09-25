import * as r from '@/components/fundingDetail/UsageReg/UsageInput.styled'
import { UsageInputType } from '@/types/fundingType'

const index = (props: UsageInputType) => {
  const { id, placeholder, value, setValue, idx } = props
  const checkedValue =
    value && id === 'amount' ? value.toLocaleString() + ' 원' : value || ''

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault()
    const updatedValue =
      id === 'amount'
        ? Number(e.target.value.split(',').join(''))
        : id === 'count'
          ? Number(e.target.value)
          : e.target.value
    setValue(prevData => {
      const updatedData = [...prevData]
      updatedData[idx] = {
        ...updatedData[idx],
        [id]: updatedValue,
      }
      return updatedData
    })
  }

  return (
    <r.Input
      placeholder={placeholder}
      value={checkedValue}
      onChange={handleChange}
      readOnly
    />
  )
}

export default index
