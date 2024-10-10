import Navbar from '@common/Navbar'
import HomeHeader from '@/components/home/HomeHeader'
import HomeTopSection from '@components/home/HomeTopSection'
import HomeContributors from '@components/home/HomeContributors'
import HomeTotalAmount from '@components/home/HomeTotalAmount'
import HomeReview from '@components/home/HomeReview'
import HomeSoonOver from '@components/home/HomeSoonOver'
import * as h from '@pages/home/HomePage.styled'
import Seo from '@/common/Seo'
import axios from 'axios'
import { useEffect } from 'react'
import { publicRequest } from '@utils/requestMethods.tsx'

const HomePage = () => {
  const getTest = async () => {
    const res = await publicRequest
      .get('/api/participants')
      .then(a => console.log(a))
  }

  useEffect(() => {
    getTest()
  }, [])

  return (
    <>
      <Seo title="GIVEUS" description="여러분의 따뜻한 기부를 기다립니다" />
      <HomeHeader />
      <h.Wrap>
        <HomeTopSection />
        <HomeContributors />
        {/* <HomeTotalAmount /> */}
        {/* <HomeReview /> */}
        {/* <HomeSoonOver /> */}
      </h.Wrap>
      <Navbar current="home" />
    </>
  )
}

export default HomePage
