import { colors } from '@/styles/theme'
import { styled } from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 1%;
  padding: 1% 2%;
  border-radius: 15px;
  height: 80%;
  overflow-y: scroll;
  -ms-overflow-style: none;
  scrollbar-width: none;

  &::-webkit-scrollbar {
    display: none;
    width: 0;
    height: 0;
    background: transparent;
    -webkit-appearance: none;
  }
`

export const OCRSection = styled.div`
  display: flex;
  gap: 30px;
`

export const OCRColWrap = styled.div`
  display: flex;
  flex-direction: column;
  margin-block: 10px 0px;
  gap: 10px;
`

export const Title = styled.div`
  font-weight: 600;
`

export const Box = styled.div`
  display: flex;
  gap: 30px;
  padding: 0 30px 20px 30px;
  background-color: #fff;
  border-radius: 8px;
  border: 1px solid ${colors.gray03};
`

export const Col = styled.div`
  display: flex;
  flex-direction: column;
`

export const BirthContainer = styled.div`
  display: flex;
  align-items: center;
  margin-block: 20px 0px;
`

export const BirthLabel = styled.label`
  margin: 0 10px 5px 0;
  width: 60px;
  font-size: 0.8em;
  font-weight: 600;
`

export const BirthWrap = styled.div`
  display: flex;
  justify-content: space-between;
  width: 300px;
`

export const SelfSection = styled.div`
  display: flex;
  width: 95%;
`

export const SelfColWrap = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 30px auto;
  gap: 10px;
`

export const Wrap = styled.div`
  display: flex;
  align-items: center;
  margin: auto;
  width: 300px;
`

export const BlueButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  background-color: ${colors.blue01};
  color: #fff;
  height: 49px;
  border-radius: 5px;
  font-size: 1em;
`
