import { currentNavState } from '@/store/common'
import * as p from '@components/privKey/PrivKey.styled'
import { useNavigate } from 'react-router-dom'
import { useSetRecoilState } from 'recoil'

type PrivKeyProp = {
  privateKey: string
  address: string
  onClose: () => void
}

const index = (props: PrivKeyProp) => {
  const { privateKey, address, onClose } = props
  const setCurrentNav = useSetRecoilState(currentNavState)
  const navigate = useNavigate()

  const handleCopy = (text: string) => {
    try {
      navigator.clipboard.writeText(text)
      alert('클립보드에 복사되었습니다.')
    } catch (error) {
      alert('클립보드 복사에 실패하였습니다.')
    }
  }

  const handleButtonClick = () => {
    setCurrentNav({ name: 'Funding List', url: '/funding', label: '펀딩 목록' })
    navigate('/funding')
    onClose()
  }

  return (
    <p.Container>
      <p.Content color="red">개인키를 안전한 곳에 보관하세요!</p.Content>
      <p.Content>
        개인키는 유출되면 재산을 잃을 수 있습니다. 절대로 타인에게 공개하지
        마세요.
      </p.Content>
      <p.Data>
        * 개인키 :&nbsp;{privateKey}{' '}
        <p.Icon
          src="/icon/icon_copy_blue.png"
          onClick={() => handleCopy(privateKey)}
        />
      </p.Data>
      <p.Data>
        * 이더리움 계정 주소 :&nbsp;{address}{' '}
        <p.Icon
          src="/icon/icon_copy_blue.png"
          onClick={() => handleCopy(address)}
        />
      </p.Data>
      <p.Button onClick={() => handleButtonClick()}>확인</p.Button>
    </p.Container>
  )
}

export default index
