import styled from "styled-components"
import { colors } from "@/styles/theme"

export const Container = styled.div`
  width: 592px;
  height: 124px;
  background-color: ${colors.blue01};
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
  color: white;
  font-size: 20px;
  
`

export const Address = styled.div`
  width: 230px;
  height: 33px;
  background-color: #fff;
  display: flex;
  justify-content: end;
  align-items: center;
`


export const Img = styled.img`
  width: 20px;
  height: 20px;
  margin-right: 10px;
  cursor: pointer;


`

