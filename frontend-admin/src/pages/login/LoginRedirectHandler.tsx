import { currentNavState } from '@/store/common'
import { adminState } from '@/store/user'
import { fetchUserInfo, loginSuccess } from '@apis/auth'
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useSetRecoilState } from 'recoil'

const LoginRedirectHandler = () => {
  const setadminState = useSetRecoilState(adminState)
  const setCurrentNav = useSetRecoilState(currentNavState)
  const url = new URL(window.location.href).searchParams

  const navigate = useNavigate()

  useEffect(() => {
    setStorage()
  }, [url])

  const setStorage = () => {
    const type = url.get('type')

    if (type == 'register') {
      // 회원가입 필요
      const provider = url.get('provider')
      const email = url.get('email')
      localStorage.setItem(
        'register',
        JSON.stringify({ provider: provider, email: email }),
      )
      window.location.href = '/signup'
    } else {
      // 로그인 성공
      const accessToken = url.get('accessToken')
      accessToken &&
        loginSuccess({ accessToken })
          .then(() =>
            fetchUserInfo().then(res => {
              console.log('로그인 성공')
              console.log(res.data)
              setadminState(res.data.data)
              setCurrentNav({
                name: '등록한 펀딩 조회',
                url: '/funding',
                label: '펀딩 목록',
              })
              navigate('/funding')
            }),
          )
          .catch(err => console.log('로그인 실패', err))
    }
  }

  return (
    <div>
      <div>login redirect page</div>
    </div>
  )
}

export default LoginRedirectHandler
