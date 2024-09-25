import * as d from '@/components/fundingDetail/DetailOCRInfo/DetailOCRInfo.styled'
import { fundingDetailState } from '@/store/funding'
import { useRecoilValue } from 'recoil'
import DetailInput from '../DetailInput'
import DetailTextArea from '../DetailInput/DetailTextArea'

const index = () => {
  const fundingDetail = useRecoilValue(fundingDetailState)

  return (
    <d.Container>
      <d.Title>상세 정보</d.Title>
      <d.Box>
        <d.Col>
          <DetailInput label="발행 번호" value={fundingDetail.issueNumber} />
          <DetailInput label="환자 성명" value={fundingDetail.patientName} />
          <DetailInput label="생년월일" value={fundingDetail.birth} />
          <DetailInput label="질병명" value={fundingDetail.diseaseName} />
          <DetailInput label="병명 코드" value={fundingDetail.diseaseCode} />
        </d.Col>
        <d.Col>
          <DetailInput
            label="환자 번호"
            value={fundingDetail.registrationNumber}
          />
          <DetailInput label="진단일" value={fundingDetail.diagnosisDate} />
          <DetailTextArea
            label="치료 내용 / 향후 치료에 대한 소견"
            value={fundingDetail.opinion}
          />
        </d.Col>
      </d.Box>
    </d.Container>
  )
}

export default index
