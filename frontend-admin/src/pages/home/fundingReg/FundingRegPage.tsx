import RegFile from '@/components/fundingReg/RegFile'
import RegInput from '@/components/fundingReg/RegInput'
import RegNumber from '@/components/fundingReg/RegNumber'
import RegGender from '@components/fundingReg/RegGender'
import OCRInput from '@components/fundingReg/OCRInput'
import OCRTextArea from '@components/fundingReg/OCRTextArea'
import { adminState } from '@/store/user'
import { OCRResult, RegDataType } from '@/types/fundingType'
import * as f from '@pages/home/fundingReg/FundingRegPage.styled'
import { useState } from 'react'
import { useMutation } from '@tanstack/react-query'
import { useRecoilValue, useSetRecoilState } from 'recoil'
import { createFirstReg } from '@/apis/funding'
import { useNavigate } from 'react-router-dom'
import { TodayDate, calculateAge, divideBirth } from '@/utils/calcMethods'
import { currentNavState } from '@/store/common'
import { selectedFundingNoState } from '@/store/funding'
import { createFunding } from '@/utils/blocStoreMethod'

const FundingRegPage = () => {
  const setCurrentNav = useSetRecoilState(currentNavState)
  const setSelectedFundingNo = useSetRecoilState(selectedFundingNoState)
  const navigate = useNavigate()
  const admin = useRecoilValue(adminState)
  const [birthValue, setBirthValue] = useState<string>('')
  const [genderValue, setGenderValue] = useState<string>('')
  const [regData, setRegData] = useState<RegDataType>({
    // 직접입력
    phone: '',
    targetAmount: 0,
    startDate: TodayDate(),
    endDate: '',
    // OCR 입력
    issueNumber: '',
    registrationNumber: '',
    patientName: '',
    birth: '',
    gender: '',
    diseaseName: '',
    diseaseCode: '',
    diagnosisDate: '',
    opinion: '',
    // 상기 데이터로 자동 입력
    title: '',
  })

  const handleOCRResult = (results: OCRResult[]) => {
    if (results) {
      const updatedRegData = { ...regData }

      results.forEach(result => {
        const { name, inferText } = result
        if (name === 'birth') {
          setBirthValue(inferText.split('-')[0])
          setGenderValue(inferText.split('-')[1][0])

          const birth = divideBirth(inferText)
          updatedRegData['birth'] = birth
          updatedRegData['gender'] = inferText
            .replace(/\s/g, '')
            .split('-')[1]
            .startsWith('1' || '3')
            ? 'M'
            : 'F'
        } else if (name === 'diagnosisDate') {
          updatedRegData['diagnosisDate'] = inferText
            .replace(/\s/g, '')
            .replace('년', '-')
            .replace('월', '-')
            .replace('일', '')
        } else {
          updatedRegData[name] = inferText
        }
      })
      setRegData(updatedRegData)
    }
  }

  const { mutate } = useMutation({
    mutationKey: ['createFirReg'],
    mutationFn: createFirstReg,
    onSuccess(result) {
      console.log('등록 성공', result)
      setCurrentNav({
        name: '등록한 펀딩 조회',
        url: `/funding`,
        label: '펀딩 목록',
      })
      setSelectedFundingNo(result.id)
      navigate(`/funding/${result.id}`)
    },
    onError(error) {
      console.error('등록 실패:', error)
      alert('펀딩 등록에 실패하였습니다.내용을 다시 확인해주세요.')
    },
  })

  const handleCreateFirstReg = async () => {
    if (new Date(regData.startDate) >= new Date(regData.endDate)) {
      alert('펀딩 종료일을 다시 설정해 주세요.')
      return
    }

    const age = calculateAge(regData.birth)
    const gender = regData.gender === 'M' ? '남' : '여'

    // 여기서 gender -> 1,3 -> M 수정
    // 970227 -> 19970227

    mutate({
      ...regData,
      adminNo: admin.adminNo,
      title: `${regData.diseaseName} ${age}세(${gender}) 펀딩`,
    })

    // 펀딩 정보 블록체인 저장
    createFunding({
      goalAmount: regData.targetAmount,
      startTime: regData.startDate,
      endTime: regData.endDate,
      title: `${regData.diseaseName} ${age}세(${gender}) 펀딩`,
      hospitalName: admin.name,
    })
  }

  return (
    <f.Container>
      {/* OCR 자동 입력 */}
      <f.OCRSection>
        <RegFile onOCRResult={handleOCRResult} />
        <f.OCRColWrap>
          <f.Title>상세 정보</f.Title>
          <f.Box>
            <f.Col>
              <OCRInput
                id="issueNumber"
                label="발행번호"
                placeholder="발행번호 입력"
                value={regData.issueNumber}
                setValue={setRegData}
              />
              <OCRInput
                id="patientName"
                label="환자 성명"
                placeholder="환자 성명 입력"
                value={regData.patientName}
                setValue={setRegData}
              />
              <f.BirthContainer>
                <f.BirthLabel>생년월일</f.BirthLabel>
                <f.BirthWrap>
                  <RegNumber
                    id="regNumber"
                    label="환자 주민등록번호"
                    placeholder="생년월일 6자리"
                    value={birthValue}
                    setValue={setBirthValue}
                  />
                  <RegGender
                    id={'regGender'}
                    label={''}
                    placeholder={''}
                    value={genderValue}
                    setValue={setGenderValue}
                  />
                </f.BirthWrap>
              </f.BirthContainer>
              <OCRInput
                id="diseaseName"
                label="질병명"
                placeholder="질병명 입력"
                value={regData.diseaseName}
                setValue={setRegData}
              />
              <OCRInput
                id="diseaseCode"
                label="병명 코드"
                placeholder="병명 코드 입력"
                value={regData.diseaseCode}
                setValue={setRegData}
              />
            </f.Col>
            <f.Col>
              <OCRInput
                id="registrationNumber"
                label="환자번호"
                placeholder="환자번호 입력"
                value={regData.registrationNumber}
                setValue={setRegData}
              />
              <OCRInput
                id="diagnosisDate"
                label="진단일"
                placeholder="진단일 입력 ( YYYY-MM-DD )"
                value={regData.diagnosisDate}
                setValue={setRegData}
              />
              <OCRTextArea
                id="opinion"
                label="치료 내용 / 향후 치료에 대한 소견"
                placeholder="치료 내용 입력"
                value={regData.opinion}
                setValue={setRegData}
              />
            </f.Col>
          </f.Box>
        </f.OCRColWrap>
      </f.OCRSection>
      {/* 직접 입력 */}
      <f.SelfSection>
        <f.SelfColWrap>
          <f.Title>기본 정보</f.Title>
          <f.Box>
            <f.Col>
              <RegInput
                id="phone"
                label="보호자 휴대폰 번호"
                placeholder="휴대폰 번호 11자리 입력 ( 0000000000 )"
                value={regData.phone}
                setValue={setRegData}
              />
              <RegInput
                id="targetAmount"
                label="펀딩 목표 금액"
                placeholder="펀딩 금액 입력"
                value={''}
                setValue={setRegData}
              />
            </f.Col>
            <f.Col>
              <RegInput
                id="startDate"
                label="펀딩 시작일"
                placeholder="펀딩 시작일 입력 ( YYYY-MM-DD )"
                value={regData.startDate}
                setValue={setRegData}
              />
              <RegInput
                id="endDate"
                label="펀딩 종료일"
                placeholder="펀딩 종료일 입력 ( YYYY-MM-DD )"
                value={regData.endDate}
                setValue={setRegData}
              />
            </f.Col>
          </f.Box>
        </f.SelfColWrap>
      </f.SelfSection>
      <f.Wrap>
        <f.BlueButton onClick={() => handleCreateFirstReg()}>
          등록하기
        </f.BlueButton>
      </f.Wrap>
    </f.Container>
  )
}

export default FundingRegPage
