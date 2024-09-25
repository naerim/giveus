import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  position: absolute;
  bottom: 0;
  right: 5%;
  width: 75%;
  margin-bottom: 1%;
  text-shadow:
    -1.2px 0 rgb(255, 255, 255, 0.2),
    0 1.2px rgb(255, 255, 255, 0.2),
    1.2px 0 rgb(255, 255, 255, 0.2),
    0 -1.2px rgb(255, 255, 255, 0.2);
`

export const Wrap = styled.div`
  display: flex;
  justify-content: space-between;
  margin: auto;
`

export const Copyright = styled.div`
  font-size: 0.8em;
  color: ${colors.gray04};
`

export const Blue = styled.a`
  color: ${colors.blue01};
  cursor: pointer;
`

export const Menu = styled.div`
  display: flex;
  justify-content: space-between;
  width: 30%;
  font-size: 0.8em;
  color: ${colors.gray04};
`

export const Text = styled.div``
