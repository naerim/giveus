import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-block: 20px 0px;
`

export const Label = styled.label`
  margin: 10px 0 10px 0;
  font-size: 0.8em;
  font-weight: 600;
`

export const TextArea = styled.textarea`
  height: 200px;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid ${colors.gray03};
`
