// import styled from 'styled-components'
// import { colors } from '@/styles/theme'

import WalletHeader from '@components/wallet/walletHeader'
import WalletSection from '@components/wallet/walletSection'

// const StyledWalletContainer = styled.div`
//   width: 592px;
//   height: 536px;
//   position: relative;
//   display: flex;
//   z-index: 0;
//   background-color: ${colors.gray02};
//   justify-content: center;
//   align-items: center;
// `



// const StyledAddress = styled.div`
//   width: 228px;
//   height: 33px;
//   background-color: #fff;
// `

const MyPage = () => {
  return (
    <>
      <WalletHeader/>
      <WalletSection/>
      
      MyPage
    </>
  )
}

export default MyPage
