import { colors } from '@styles/theme'
import styled from 'styled-components'

export const BlackBox = styled.div`
  position: fixed;
  left: 0;
  top: 0;
  z-index: 100;
  width: 100vw;
  height: 100vh;
  background-color: #000;
  opacity: 0.3;
`

export const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
`

export const Wrap = styled.div`
  position: relative;
  z-index: 101;
  display: flex;
  flex-direction: column;
  background-color: #fff;
  border-radius: 12px;
  width: 70%;
  height: 80%;
  overflow-y: scroll;
  &::-webkit-scrollbar {
    display: none; /* 크롬, 사파리, 오페라, 엣지 */
    width: 0; /* Remove scrollbar space */
    height: 0;
    background: transparent; /* Optional: just make scrollbar invisible */
    -webkit-appearance: none;
  }
`

export const ModalName = styled.div`
  display: flex;
  justify-content: center;
  padding-block: 1em;
  border-bottom: 2px solid ${colors.gray02};
  font-weight: 600;
`

export const Backdrop = styled.div`
  position: absolute;
  top: 0;
  right: 0;
  margin: 1em;
  img {
    cursor: pointer;
    width: 16px;
    height: 16px;
  }
`
