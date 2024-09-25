import * as l from '@components/login/LoginHeader/LoginHeader.styled'

const index = () => {
  return (
    <l.Container>
      <l.Logo>
        <img src="/img/img_logo.png" />
      </l.Logo>
      <l.Button>
        <l.Icon src="/icon/icon_signup.png" alt="" />
        로그인해 주시기 바랍니다
      </l.Button>
      <l.AppButton onClick={() => window.open('https://giveus.site', '_blank')}>
        GIVEUS APP
      </l.AppButton>
    </l.Container>
  )
}

export default index
