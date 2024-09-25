import { FullButtonType } from '@/types/commonType'
import * as f from '@common/Fullbutton/Fullbutton.styled'

const index = (props: FullButtonType) => {
  const { text, disabled, onClick } = props

  return (
    <f.Button disabled={disabled} onClick={onClick}>
      {text}
    </f.Button>
  )
}

export default index
