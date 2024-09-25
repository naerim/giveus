import { colors } from '@/styles/theme'
import { styled } from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 5%;
  align-items: flex-start;
  gap: 30px;
`

export const Content = styled.div`
  font-weight: 600;
  color: ${props => props.color};
`

export const Data = styled.div`
  display: flex;
  font-size: 0.9em;
`

export const Ref = styled.div``

export const Icon = styled.img`
  margin-left: 10px;
  width: 20px;
  height: 20px;
  cursor: pointer;
`

export const Button = styled.button`
  margin-inline: auto;
  width: 200px;
  height: 60px;
  border-radius: 10px;
  background-color: ${colors.blue01};
  color: white;
  font-size: 20px;
  font-weight: 600;
`
