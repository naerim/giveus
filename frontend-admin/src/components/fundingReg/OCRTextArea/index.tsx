import * as o from '@/components/fundingReg/OCRTextArea/OCRTextArea.styled'
import { RegInputType } from '@/types/fundingType'

const index = (props: RegInputType) => {
  const { id, label, placeholder, value, setValue } = props

  const handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    e.preventDefault()
    setValue(prevData => ({
      ...prevData,
      [id]: e.target.value,
    }))
  }

  return (
    <o.Container>
      <o.Label htmlFor={id}>{label}</o.Label>
      <o.TextArea
        id={id}
        placeholder={placeholder}
        value={value}
        onChange={handleChange}
      />
    </o.Container>
  )
}

export default index
