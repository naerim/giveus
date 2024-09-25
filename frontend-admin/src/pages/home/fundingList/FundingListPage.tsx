import { fetchFundingList } from '@/apis/funding'
import Table from '@/components/fundingList/Table'
import { fundingState } from '@/store/funding'
import * as f from '@pages/home/fundingList/FundingListPage.styled'
import { useQuery } from '@tanstack/react-query'
import { useEffect, useMemo } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil'
import { columnsData } from './data'
import { adminState } from '@/store/user'

const FundingListPage = () => {
  const setFunding = useSetRecoilState(fundingState)
  const admin = useRecoilValue(adminState)

  const { data, isLoading } = useQuery({
    queryKey: ['FundingList'],
    queryFn: () => fetchFundingList(admin.adminNo),
  })

  useEffect(() => {
    !isLoading && setFunding(data)
  }, [data, isLoading])

  const columns = useMemo(() => columnsData, [])

  return (
    <f.Container>{data && <Table columns={columns} data={data} />}</f.Container>
  )
}

export default FundingListPage
