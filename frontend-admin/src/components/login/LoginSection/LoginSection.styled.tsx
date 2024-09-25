import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 40%;
  height: 100vh;
  justify-content: center;
  align-items: center;
  row-gap: 5%;
`

export const Desc = styled.div`
  color: ${colors.blue01};
  font-size: 2em;
  font-weight: 600;
`

export const SubDesc = styled.div`
  color: ${colors.gray04};
`

export const Button = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: ${colors.yellow01};
  color: #3d1d1c;
  font-weight: 700;
  font-size: 0.9em;
  width: 100%;
  height: 49px;
  border-radius: 5px;
  margin-bottom: 50%;

  img {
    width: 17px;
    height: 16px;
    margin-right: 9px;
  }
`
