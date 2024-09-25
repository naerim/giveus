import { BASE_URL } from '@utils/requestMethods'
import * as l from '@components/login/LoginSection/LoginSection.styled'

const index = () => {
  const onClick = () => {
    // const LOCAL_URL = 'http://localhost:8086'
    const kakaoURL = `${BASE_URL}/admin/oauth2/authorization/kakao`
    window.location.href = kakaoURL
  }
  return (
    <l.Container>
      <l.Desc>Welcome Back</l.Desc>
      <l.SubDesc>GIVEUS 병원 페이지에 오신 것을 환영합니다.</l.SubDesc>
      <l.Button onClick={onClick}>
        <img src="/icon/icon_kakao.png" alt="" />
        카카오로 시작하기
      </l.Button>
    </l.Container>
  )
}

export default index
