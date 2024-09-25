import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px 0;
  gap: 10px;
  width: 32%;
`

export const Title = styled.div`
  font-weight: 600;
`

export const Box = styled.div`
  display: flex;
  background-color: #fff;
  border-radius: 8px;
  border: 1px solid ${colors.gray03};
  gap: 30px;
  padding: 0 30px 20px 30px;
`

export const Col = styled.div`
  display: flex;
  flex-direction: column;
`
