import { colors } from '@/styles/theme'
import { styled } from 'styled-components'

export const Container = styled.div`
  display: flex;
  margin: 1%;
  height: 80%;
`

export const Wrap = styled.div`
  display: flex;
  flex-direction: column;
`

export const Line = styled.div`
  position: relative;
  width: 100%;
  height: 5px;
  background: linear-gradient(
    to right,
    #fff,
    #defcff,
    #74f0fe,
    #53efff,
    ${colors.blue01}
  );
  border-radius: 20px;
  top: 80px;
`

export const InfoBox = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-top: 20px;
`
