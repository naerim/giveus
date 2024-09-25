import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 2%;
  width: 100%;
  margin-inline: auto;
  padding: 1%;
  border-radius: 15px;
  border: 1.5px solid #fff;

  background: linear-gradient(
    113deg,
    rgba(255, 255, 255, 0.82) 0%,
    rgba(255, 255, 255, 0.8) 110.84%
  );

  box-shadow: 0px 7px 23px 0px rgba(0, 0, 0, 0.05);
  backdrop-filter: blur(10.500000953674316px);
`

export const Logo = styled.div`
  img {
    width: 120px;
  }
  margin-left: 20px;
  cursor: pointer;
`

export const Button = styled.button`
  display: flex;
  align-items: center;
`

export const Icon = styled.img`
  width: 20px;
  margin-right: 5px;
`

export const AppButton = styled.button`
  margin-right: 0;
  border-radius: 34.5px;
  background: linear-gradient(82deg, #313860 2.25%, #151928 79.87%);
  color: #fff;
  padding: 8px 8%;
`
