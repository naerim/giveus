import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 1%;
  padding-inline: 2%;
`

export const LeftWrap = styled.div`
  display: flex;
  flex-direction: column;
`

export const SubWrap = styled.div`
  display: flex;
  margin-bottom: 10px;
`

export const Pages = styled.div`
  color: ${colors.gray04};
  font-size: 0.8em;
`

export const CurrentPage = styled.div`
  font-size: 0.8em;
`

export const TitlePage = styled.div`
  font-size: 1em;
  font-weight: 600;
`

export const RightWrap = styled.div`
  display: flex;
  justify-content: end;
  align-items: center;
  right: 0;
`

export const Icon = styled.img`
  width: 18px;
  height: 18px;
  margin-left: 50%;
  cursor: pointer;
`
