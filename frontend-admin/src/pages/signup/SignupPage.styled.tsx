import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 60%;
  margin: 0 auto;
  height: 100vh;
`

export const BackgroundImg = styled.img`
  position: absolute;
  width: 45%;
  right: 0;
  z-index: -1;
`

export const SignupSection = styled.div`
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
  font-size: 1.5em;
  font-weight: 600;
`

export const SubDesc = styled.div`
  color: ${colors.gray04};
`

export const Input = styled.input`
  padding: 15px;
  border-radius: 5px;
  border: 1px solid ${colors.gray04};
  font-size: 1.1em;
`

export const Button = styled.button`
  background-color: ${colors.blue01};
  color: #fff;
  font-weight: 600;
  font-size: 0.9em;
  width: 100%;
  height: 49px;
  border-radius: 5px;
  margin-bottom: 50%;
`
