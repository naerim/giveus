import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: space-between;
  top: 0;
  margin-left: 20px;
`

export const Wrap = styled.div`
  display: flex;
  align-items: center;
  gap: 20px;
`

export const ImgBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  background-color: ${colors.gray02};
  border-radius: 10px;
`

export const Img = styled.img`
  width: 80%;
  height: 80%;
  border-radius: 10px;
`

export const Title = styled.div`
  font-size: 1.2em;
  font-weight: 600;
  white-space: nowrap;
`

export const Button = styled.button<{
  $isDisabled: boolean
}>`
  border-radius: 15px;
  margin-right: 50px;
  padding: 8px 20px;
  color: #fff;
  background-color: ${colors.yellow01};
  font-size: 0.8em;
  opacity: ${props => (props.$isDisabled ? '0.5' : '1')};
  border: 0.5px solid gray;
  text-shadow:
    -1px 0 1px ${colors.gray05},
    0 1px 1px ${colors.gray05},
    1px 0 1px ${colors.gray05},
    0 -1px 1px ${colors.gray05};
`
