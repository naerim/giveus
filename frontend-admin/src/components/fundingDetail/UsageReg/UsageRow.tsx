import * as u from '@/components/fundingDetail/UsageReg/UsageRow.styled'
import UsageInput from './UsageInput'
import { UsageRegType } from '@/types/fundingType'

const UsageRow = (props: UsageRegType) => {
  const { idx, regData, setRegData } = props

  return (
    <u.Container>
      <u.Idx>{idx + 1}번</u.Idx>
      <UsageInput
        id="category"
        placeholder="자동 입력됩니다"
        value={regData.category}
        setValue={setRegData}
        idx={idx}
      />
      <UsageInput
        id="content"
        placeholder="자동 입력됩니다"
        value={regData.content}
        setValue={setRegData}
        idx={idx}
      />
      <UsageInput
        id="count"
        placeholder="자동 입력됩니다"
        value={regData.count}
        setValue={setRegData}
        idx={idx}
      />
      <UsageInput
        id="amount"
        placeholder="자동 입력됩니다"
        value={regData.amount}
        setValue={setRegData}
        idx={idx}
      />
    </u.Container>
  )
}

export default UsageRow
