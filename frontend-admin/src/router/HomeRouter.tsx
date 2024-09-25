import ReviewRegOkPage from '@/pages/reviewReg/ReviewRegOkPage'
import ReviewRegPage from '@/pages/reviewReg/ReviewRegPage'
import SignupPage from '@pages/signup/SignupPage'
import LoginPage from '@pages/login/LoginPage'
import { Route, Routes } from 'react-router-dom'
import LoginRedirectHandler from '@pages/login/LoginRedirectHandler'

const HomeRouter = () => {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/login/oauth2" element={<LoginRedirectHandler />} />
      <Route path="/signup" element={<SignupPage />} />
      <Route path="/giveus/review/:id" element={<ReviewRegPage />} />
      <Route path="/giveus/review-ok" element={<ReviewRegOkPage />} />
      <Route path="/*" element={<LoginPage />} />
    </Routes>
  )
}

export default HomeRouter
