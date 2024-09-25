import { BrowserRouter } from 'react-router-dom'
import { GlobalStyle } from './styles/globalStyle'
import HomeRouter from './router/HomeRouter'
import AuthRouter from './router/AuthRouter'
import { useRecoilValue } from 'recoil'
import { adminState } from './store/user'

function App() {
  const admin = useRecoilValue(adminState)

  return (
    <BrowserRouter>
      <GlobalStyle />
      {admin.adminNo > 0 ? <AuthRouter /> : <HomeRouter />}
    </BrowserRouter>
  )
}

export default App
