import { fetchFundingDetail } from '@/apis/funding'
import DetailNoData from '@/components/fundingDetail/DetailNoData'
import DetailOCRInfo from '@/components/fundingDetail/DetailOCRInfo'
import DetailReg from '@/components/fundingDetail/DetailReg'
import DetailSelfInfo from '@/components/fundingDetail/DetailSelfInfo'
import { fundingDetailState, selectedFundingNoState } from '@/store/funding'
import * as f from '@pages/home/fundingDetail/FundingDetailPage.styled'
import { useQuery } from '@tanstack/react-query'
import { useEffect } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil'

const FundingDetailPage = () => {
  const selectedFundingNo = useRecoilValue(selectedFundingNoState)
  const setFundingDetail = useSetRecoilState(fundingDetailState)

  const { data, isLoading } = useQuery({
    queryKey: ['FundingDetail'],
    queryFn: () => selectedFundingNo && fetchFundingDetail(selectedFundingNo),
  })

  useEffect(() => {
    !isLoading && setFundingDetail(data)
  }, [data, isLoading])

  return (
    <f.Container>
      {!selectedFundingNo ? (
        <DetailNoData />
      ) : (
        <f.Wrap>
          <f.Line />
          <DetailReg />
          <f.InfoBox>
            <DetailOCRInfo />
            <DetailSelfInfo />
          </f.InfoBox>
        </f.Wrap>
      )}
    </f.Container>
  )
}

export default FundingDetailPage
