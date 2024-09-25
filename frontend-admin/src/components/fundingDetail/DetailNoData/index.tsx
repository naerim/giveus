import Fullbutton from '@/common/Fullbutton'
import * as d from '@/components/fundingDetail/DetailNoData/DetailNoData.styled'
import { currentNavState } from '@/store/common'
import { useNavigate } from 'react-router-dom'
import { useSetRecoilState } from 'recoil'

const index = () => {
  const setCurrentNav = useSetRecoilState(currentNavState)
  const navigate = useNavigate()

  const onClick = () => {
    setCurrentNav({ name: 'Funding List', url: '/funding', label: '펀딩 목록' })
    navigate('/funding')
  }

  return (
    <d.Container>
      <d.Desc>선택된 펀딩이 없습니다. 펀딩을 선택해 주세요.</d.Desc>
      <Fullbutton text={'펀딩 목록'} disabled={false} onClick={onClick} />
    </d.Container>
  )
}

export default index
