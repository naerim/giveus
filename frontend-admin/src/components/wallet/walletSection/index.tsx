import * as ws from '@components/wallet/walletSection/WalletSection.styled'
import styled from 'styled-components'


const Wrap = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 20px;

`
const SubWrap = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;

`


const index = () => {
  return (
    <>
      <ws.Container>
        <Wrap>
          <SubWrap>
            <ws.Img src="/icon/icon_coin_Bitcoin.png" />
            Bitcoin
          </SubWrap>
          0 BTC
        </Wrap>
        <Wrap>
          <SubWrap>
            <ws.Img src="/icon/icon_coin_Ethereum.png" />
            Ethereum
          </SubWrap>
          0 ETH
        </Wrap>
        <Wrap>
          <SubWrap>
            <ws.Img src="/icon/icon_coin_XRP.png" />
            Ripple
          </SubWrap>
          0 BTC
        </Wrap>
        <Wrap>
          <SubWrap>
            <ws.Img src="/icon/icon_coin_Dogecoin.png" />
            Bitcoin
          </SubWrap>
          0 BTC
        </Wrap>
      </ws.Container>
    </>
  )
}

export default index
