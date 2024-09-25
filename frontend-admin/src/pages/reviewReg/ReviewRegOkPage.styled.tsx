import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 90%;
  padding-bottom: 18px;
  min-width: 360px;
  max-width: 430px;
  min-height: 100vh;
  margin: 0 auto;
  @media only screen and (min-width: 430px) {
    width: 430px;
  }
  @media only screen and (min-width: 600px) {
    width: 375px;
  }
`

export const Top = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50%;
`

export const Title = styled.div`
  font-weight: 700;
  font-size: 1.5em;
`

export const Icon = styled.img`
  width: 65px;
  height: 65px;
  margin: 24px 0;
`

export const SubTitle = styled.div`
  font-weight: 500;
  font-size: 1em;
  margin-top: 20px;
`

export const Line = styled.div`
  background-color: #f4f4f4;
  width: 100%;
  height: 2px;
  margin-bottom: 13px;
`

export const Desc = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 47px;
  span {
    font-size: 0.9em;
    line-height: 1.4;
    color: ${colors.gray04};
  }
`

export const Bottom = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: auto;
`
