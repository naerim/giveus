import LoginFooter from '@/components/login/LoginFooter'
import LoginHeader from '@/components/login/LoginHeader'
import LoginSection from '@/components/login/LoginSection'
import * as l from '@pages/login/LoginPage.styeld'

const LoginPage = () => {
  return (
    <l.Container>
      <LoginHeader />
      <l.BackgroundImg src="/img/img_login_bg.png" alt="bg" />
      <LoginSection />
      <LoginFooter />
    </l.Container>
  )
}

export default LoginPage
