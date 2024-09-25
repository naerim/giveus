import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  align-items: center;
  margin-block: 20px 0px;
`

export const Label = styled.label`
  margin: 0 10px 5px 0;
  width: 100px;
  font-size: 0.8em;
  font-weight: 600;
`

export const Input = styled.input`
  width: 210px;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid ${colors.gray03};
  background-color: ${colors.gray02};
`
