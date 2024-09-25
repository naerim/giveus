import { StringStateType } from '@/types/commonType'
import * as r from '@components/reviewReg/ReviewRegContent/ReviewRegContent.styled'

const index = (props: StringStateType) => {
  const { value, setValue } = props

  const handleValue = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    e.preventDefault()
    setValue(e.target.value)
  }

  return (
    <r.Container>
      <r.Desc>소개글을 작성해주세요</r.Desc>
      <r.SubDesc
        value={value}
        onChange={handleValue}
        placeholder="이 내용은 후기글에 작성됩니다.&#13;&#10;&#13;&#10;환자의 현재상태, 향후 관리 사항에 대한 간략한 정보를 포함해주세요.&#13;&#10;&#13;&#10;더 많은 사람들에게 기부를 독려하도록 글을 작성해주세요."
      ></r.SubDesc>
    </r.Container>
  )
}

export default index
