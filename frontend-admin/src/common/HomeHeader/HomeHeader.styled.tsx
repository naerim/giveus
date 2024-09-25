import styled from 'styled-components'

export const Container = styled.header`
  display: flex;
  position: fixed;
  width: 100%;
  min-width: 360px;
  max-width: 430px;
  top: 0;
  height: 45px;
  z-index: 100;
  background-color: rgba(255, 255, 255, 0.8);

  @media only screen and (min-width: 430px) {
    width: 430px;
  }
  @media only screen and (min-width: 600px) {
    width: 375px;
  }
`

export const Wrap = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 90%;
  margin: 0 auto;
`

export const Logo = styled.div`
  width: 100px;
  height: 35px;

  img {
    width: 100%;
    height: 100%;
  }
`
