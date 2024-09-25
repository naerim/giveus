import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`

export const RowBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 90%;
`

export const RowTitleBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-inline: 58px 20px;
  margin-bottom: 10px;
`

export const RowTitle = styled.div`
  width: 25%;
  text-align: center;

  font-weight: 600;
`

export const PlusButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  border: 1px solid ${colors.gray03};
  width: 50px;
  height: 50px;
  margin-block: 20px;
`

export const Icon = styled.img`
  width: 50%;
  height: 50%;
`

export const Wrap = styled.div`
  width: 400px;
  margin-top: 20px;
`

export const Button = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  background-color: ${colors.blue01};
  color: #fff;
  height: 49px;
  border-radius: 5px;
  font-size: 1em;
  margin-block: 0 30px;
`
