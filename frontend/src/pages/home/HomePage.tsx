import Navbar from '@common/Navbar'
import HomeHeader from '@/components/home/HomeHeader'
import HomeTopSection from '@components/home/HomeTopSection'
import HomeContributors from '@components/home/HomeContributors'
// import HomeTotalAmount from '@components/home/HomeTotalAmount'
// import HomeReview from '@components/home/HomeReview'
// import HomeSoonOver from '@components/home/HomeSoonOver'
import * as h from '@pages/home/HomePage.styled'
import Seo from '@/common/Seo'

const HomePage = () => {
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
