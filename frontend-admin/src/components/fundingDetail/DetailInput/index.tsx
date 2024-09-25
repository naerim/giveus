import * as d from '@/components/fundingDetail/DetailInput/DetailInput.styled'

const index = (props: { label: string; value: string }) => {
  const { label, value } = props

  return (
    <d.Container>
      <d.Label>{label}</d.Label>
      <d.Input value={value || ''} readOnly />
    </d.Container>
  )
}

export default index
