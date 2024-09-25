import { currentNavState } from '@/store/common'
import * as h from '@components/home/HomeHeader/HomeHeader.styled'
import { useRecoilValue } from 'recoil'

const index = () => {
  const currentNav = useRecoilValue(currentNavState)

  return (
    <h.Container>
      <h.LeftWrap>
        <h.SubWrap>
          <h.Pages>홈</h.Pages>&nbsp;/&nbsp;
          <h.CurrentPage>{currentNav.name}</h.CurrentPage>
        </h.SubWrap>
        <h.TitlePage>{currentNav.label}</h.TitlePage>
      </h.LeftWrap>
      <h.RightWrap>
        <h.Icon src="/icon/icon_profile_gray.png" />
        <h.Icon src="/icon/icon_setting_gray.png" />
        <h.Icon src="/icon/icon_bell_gray.png" />
      </h.RightWrap>
    </h.Container>
  )
}

export default index
