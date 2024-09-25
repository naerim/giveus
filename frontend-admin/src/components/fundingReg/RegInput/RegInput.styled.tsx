import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  align-items: center;
  margin-block: 20px 0px;
`

export const Label = styled.label`
  margin: 0 10px 5px 0;
  width: 120px;
  font-size: 0.8em;
  font-weight: 600;
`

export const Input = styled.input<{ $isStartDate: boolean }>`
  width: 360px;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid ${colors.gray03};
  background-color: ${props => props.$isStartDate && colors.gray02};
`
