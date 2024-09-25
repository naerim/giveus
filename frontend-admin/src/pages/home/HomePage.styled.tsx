import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  width: 100%;
  height: 100vh;
  /* background: linear-gradient(
    0deg,
    rgba(125, 168, 252, 0.6) 0%,
    ${colors.gray01} 18%
  ); */
  background-color: #f8f9fa;
`

export const Wrap = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`
