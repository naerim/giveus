import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  margin-block: 30px;
`

export const Box = styled.div`
  width: 200px;
  height: 300px;
  border-radius: 8px;
  border: 1px solid ${colors.gray03};
  background-color: #fff;
`

export const Wrap = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`

export const Icon = styled.img`
  width: 30px;
  height: 30px;
`

export const Text = styled.div`
  margin-top: 20px;
  text-align: center;
  line-height: 25px;
`

export const Input = styled.input`
  width: 0;
  height: 0;
  margin: 0;
  overflow: hidden;
`

export const BlueButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #48cef7;
  color: #fff;
  border-radius: 5px;
  font-size: 1em;
  padding: 10px 20px;
`

export const OrangeButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #ffb028;
  color: #fff;
  border-radius: 5px;
  font-size: 1em;
  padding: 10px 20px;
`

export const PreviewImage = styled.img`
  width: 90%;
  height: 90%;
  margin: auto;
  border-radius: 8px;
  cursor: pointer;
`

export const BtnLayout = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 30px;
  gap: 20px;
`
