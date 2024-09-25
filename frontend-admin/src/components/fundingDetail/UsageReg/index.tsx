import * as u from '@/components/fundingDetail/UsageReg/UsageReg.styled'
import { useState } from 'react'
import { BlockUSageType, UsageDataType } from '@/types/fundingType'
import { useRecoilValue } from 'recoil'
import { selectedFundingNoState } from '@/store/funding'
import { useMutation } from '@tanstack/react-query'
import { createFundingUsage } from '@/apis/funding'
import { BooleanStateType } from '@/types/commonType'
import DetailFile from '@components/fundingDetail/DetailFile'
import { OCRResult } from '@/types/fundingType'
import UsageRow from './UsageRow'
import { usageTitle } from '@/assets/data/usageTitle'
import { addExpense } from '@/utils/blocStoreMethod'

const Index = (props: BooleanStateType) => {
  const { setValue } = props
  const selectedFundingNo = useRecoilValue(selectedFundingNoState)
  const [regData, setRegData] = useState<UsageDataType[]>([
    {
      fundingNo: selectedFundingNo,
      category: '',
      content: '',
      amount: 0,
      count: 0,
    },
  ])
  const [blockExpense, setBlockExpense] = useState<BlockUSageType>([])

  const { mutate } = useMutation({
    mutationKey: ['createFundingUsage'],
    mutationFn: createFundingUsage,
    onSuccess(result) {
      console.log('등록 성공', result)
      setValue(false)
      alert('기금 사용 내역을 성공적으로 등록하였습니다.')
    },
    onError(error) {
      console.error('등록 실패:', error)
    },
  })

  const handleOCRResult = (results: OCRResult[]) => {
    if (results) {
      let tmpResults: UsageDataType[] = []
      let tmpBlockExpense: BlockUSageType = []
      results.map((item, idx) => {
        const tmpItem = item.inferText.split(' ')
        tmpResults[idx] = {
          fundingNo: selectedFundingNo,
          category: tmpItem[0],
          content: tmpItem[2],
          amount: Number(tmpItem[4].replace(/,/g, '')),
          count: Number(tmpItem[3]),
        }
        tmpBlockExpense[idx] = [
          tmpItem[0],
          tmpItem[1],
          tmpItem[2],
          Number(tmpItem[3]),
          Number(tmpItem[4].replace(/,/g, '')),
        ]
      })
      setBlockExpense(tmpBlockExpense)
      setRegData(tmpResults)
    }
  }

  const handleCreateFundingUsage = async () => {
    try {
      mutate(regData)
    } catch {
      alert('기금 사용 내역 등록에 실패하였습니다.내용을 다시 확인해주세요.')
      return
    }

    // 블록저장
    addExpense(blockExpense)
  }

  // const handlePlusRegData = () => {
  //   const newRegData = [
  //     ...regData,
  //     {
  //       fundingNo: selectedFundingNo,
  //       category: '',
  //       content: '',
  //       amount: 0,
  //       count: 0,
  //     },
  //   ]
  //   setRegData(newRegData)
  // }

  return (
    <u.Container>
      <DetailFile onOCRResult={handleOCRResult} />
      <u.RowBox>
        <u.RowTitleBox>
          {usageTitle.map(item => (
            <u.RowTitle key={item}>{item}</u.RowTitle>
          ))}
        </u.RowTitleBox>
        {regData.map((data, idx) => (
          <UsageRow
            key={idx}
            idx={idx}
            regData={data}
            setRegData={setRegData}
          />
        ))}
      </u.RowBox>
      {/* <u.PlusButton onClick={() => handlePlusRegData()}>
        <u.Icon src="/icon/icon_plus_blue.png" />
      </u.PlusButton> */}
      <u.Wrap>
        <u.Button onClick={() => handleCreateFundingUsage()}>
          기금 사용 내역 등록
        </u.Button>
      </u.Wrap>
    </u.Container>
  )
}

export default Index
