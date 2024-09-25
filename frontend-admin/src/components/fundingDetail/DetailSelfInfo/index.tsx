import * as d from '@/components/fundingDetail/DetailSelfInfo/DetailSelfInfo.styled'
import DetailInput from '../DetailInput'
import { useRecoilValue } from 'recoil'
import { fundingDetailState } from '@/store/funding'

const index = () => {
  const fundingDetail = useRecoilValue(fundingDetailState)

  const phoneData = fundingDetail.phone && fundingDetail.phone.split('')
  let phone =
    phoneData &&
    phoneData.slice(0, 3).join('') +
      '-' +
      phoneData.slice(3, 7).join('') +
      '-' +
      phoneData.slice(7).join('')

  return (
    <d.Container>
      <d.Title>기본 정보</d.Title>
      <d.Box>
        <d.Col>
          <DetailInput label="보호자 전화번호" value={phone} />
          <DetailInput
            label="펀딩 목표 금액"
            value={fundingDetail && fundingDetail.targetAmount.toLocaleString() + '원'}
          />
          <DetailInput label="펀딩 시작일" value={fundingDetail.startDate} />
          <DetailInput label="펀딩 종료일" value={fundingDetail.endDate} />
        </d.Col>
      </d.Box>
    </d.Container>
  )
}

export default index
