import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 18%;
  height: 100vh;
  padding: 1%;
  margin-left: 1%;
`

export const Logo = styled.div`
  img {
    width: 150px;
  }
  padding: 0 5% 0% 5%;
  position: relative;
  // &:after {
  //   content: '';
  //   position: absolute;
  //   bottom: 0;
  //   left: 0;
  //   width: 100%;
  //   height: 1.5px;
  //   background: linear-gradient(
  //     to right,
  //     ${colors.gray01},
  //     ${colors.gray04},
  //     ${colors.gray01}
  //   );
  // }
`

export const Button = styled.button<{ $active: boolean }>`
  display: flex;
  margin-top: 5%;
  padding: 5%;
  width: 100%;
  background-color: ${props => (props.$active ? '#fff' : '')};
  border-radius: 15px;
  ${props =>
    props.$active
      ? 'box-shadow: 0px 3.5px 5.5px 0px rgba(0, 0, 0, 0.1);'
      : ''};
`

export const IconBox = styled.div<{ $active: boolean }>`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 33px;
  height: 33px;
  border-radius: 12px;
  background-color: ${props => (props.$active ? colors.blue01 : '#fff')};
  margin-right: 5%;
`

export const Icon = styled.img`
  width: 20px;
  height: 20px;
`

export const Text = styled.div<{ $active: boolean }>`
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${props => (props.$active ? '#000' : colors.gray04)};
  margin-block: auto;
  font-weight: 600;
`

export const Category = styled.div`
  font-size: 1.2em;
  font-weight: 600;
  margin-block: 10% 10%;
`

export const HelpBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20%;
  border-radius: 15px;
  background-image: url('/img/img_question_bg.png');
  background-size: cover;
  padding: 5%;
`

export const Desc = styled.div`
  color: #fff;
  margin-top: 10%;
  font-size: 0.9em;
  font-weight: 600;
`

export const SubDesc = styled.div`
  margin-block: 5%;
  color: #fff;
  font-size: 0.9em;
`

export const HelpButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 12px;
  background-color: #fff;
  width: 100%;
  font-weight: 600;
  padding-block: 5%;
  margin-top: 5%;
`
