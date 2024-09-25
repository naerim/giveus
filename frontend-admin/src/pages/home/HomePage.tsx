import HomeFooter from '@/components/home/HomeFooter'
import HomeHeader from '@/components/home/HomeHeader'
import HomeNav from '@/components/home/HomeNav'
import * as h from '@pages/home/HomePage.styled'
import { useEffect } from 'react'
import { Outlet } from 'react-router'
import { useNavigate } from 'react-router-dom'

const HomePage = () => {
  const navigate = useNavigate()

  useEffect(() => {
    navigate('/funding')
  }, [])

  return (
    <h.Container>
      <HomeNav />
      <h.Wrap>
        <HomeHeader />
        <Outlet />
      </h.Wrap>
      <HomeFooter />
    </h.Container>
  )
}

export default HomePage
