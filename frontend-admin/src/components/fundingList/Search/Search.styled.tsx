import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 10px;
`

export const Form = styled.form``

export const Input = styled.input`
  border-radius: 20px;
  padding: 5px 10px;
`

export const Button = styled.button`
  border-radius: 20px;
  padding: 5px 15px;
  background-color: ${colors.blue01};
  color: #fff;
`
