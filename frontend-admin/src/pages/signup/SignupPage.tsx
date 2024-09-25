import { joinUser } from '@/apis/auth'
// import { adminState } from '@/store/UserAtom'
import { adminState } from '@/store/user'
import * as s from '@pages/signup/SignupPage.styled'
import { useState } from 'react'
import { useSetRecoilState } from 'recoil'
import {
  createPrivateKey,
  getPublicKey,
  getEthereumAddress,
} from '@/utils/walletCreation'
import Modal from '@common/Modal'
import PrivKey from '@components/privKey'
import LoginFooter from '@/components/login/LoginFooter'
import LoginHeader from '@/components/login/LoginHeader'

const SignupPage = () => {
  const [name, setName] = useState<string>('')
  const setadminState = useSetRecoilState(adminState)

  const [isModalOpen, setIsModalOpen] = useState(false) // 모달이 열려있는지 상태를 관리합니다.
  const [privateKey, setPrivateKey] = useState<string>('')
  const [address, setAddress] = useState<string>('')

  const signup = async () => {
    setIsModalOpen(true)

    // 지갑 생성 후 계정주소 서버에 저장
    let privateKey: string = createPrivateKey()
    const publicKey: string = getPublicKey(privateKey)
    const address: string = getEthereumAddress(publicKey)
    setPrivateKey(privateKey)
    setAddress(address)

    try {
      const res = await joinUser({ name, address })
      setadminState(res.data.data)
    } catch (err) {
      console.error('Error during signup:', err)
    }
  }

  return (
    <s.Container>
      <LoginHeader />
      <s.BackgroundImg src="/img/img_login_bg.png" alt="bg" />
      <s.SignupSection>
        <s.Desc>추가 입력 단계</s.Desc>
        <s.SubDesc>병원 이름을 입력해 주시기 바립니다</s.SubDesc>
        <s.Input value={name} onChange={e => setName(e.target.value)} />
        <s.Button onClick={() => signup()}>회원가입</s.Button>
      </s.SignupSection>
      {isModalOpen && (
        <Modal
          name={'개인키 관련 주의사항'}
          children={
            <PrivKey
              privateKey={privateKey}
              address={address}
              onClose={() => setIsModalOpen(false)}
            />
          }
          onClose={() => setIsModalOpen(false)}
        />
      )}
      <LoginFooter />
    </s.Container>
  )
}

export default SignupPage
