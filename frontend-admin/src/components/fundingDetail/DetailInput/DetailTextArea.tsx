import * as d from '@/components/fundingDetail/DetailInput/DetailTextArea.styled'

const DetailTextArea = (props: { label: string; value: string }) => {
  const { label, value } = props

  return (
    <d.Container>
      <d.Label>{label}</d.Label>
      <d.TextArea value={value} readOnly />
    </d.Container>
  )
}

export default DetailTextArea
