import { colors } from '@/styles/theme'
import styled from 'styled-components'

export const Container = styled.div``

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  box-shadow: 4px 4px 10px 0 rgba(0, 0, 0, 0.1);
`

export const Thead = styled.thead``

export const Tbody = styled.tbody``

export const Tr = styled.tr``

export const Th = styled.th`
  padding: 8px;
  border-bottom: 1px solid ${colors.gray03};
  background-color: rgba(67, 130, 255, 0.6);
  color: white;
  &:nth-child(1) {
    width: 8%;
  }
  &:nth-child(2) {
    width: 15%;
  }
  &:nth-child(3) {
    width: 15%;
  }
  &:nth-child(4) {
    width: 15%;
  }
  &:nth-child(5) {
    width: 15%;
  }
  &:nth-child(6) {
    width: 15%;
  }
  &:nth-child(7) {
    width: 17%;
  }
`

export const Td = styled.td`
  padding: 10px;
  text-align: center;
  border-bottom: 1px solid ${colors.gray03};
  cursor: pointer;
`

export const StatusButton = styled.button<{ $status: string }>`
  border-radius: 15px;
  background-color: ${props =>
    props.$status === '2차 등록 대기'
      ? colors.gray03
      : props.$status === '기금 사용 내역 등록'
        ? colors.yellow01
        : props.$status === '후기 등록'
          ? colors.blue01
          : colors.orange01};
  width: 130px;
  font-size: 0.8em;
  padding: 4px;
  color: #fff;
  text-shadow:
    -1px 0 1px ${colors.gray05},
    0 1px 1px ${colors.gray05},
    1px 0 1px ${colors.gray05},
    0 -1px 1px ${colors.gray05};
`

export const PageBox = styled.div`
  position: absolute;
  bottom: 8px;
  width: 96%;
  display: flex;
  justify-content: center;
  margin-block: 10px;
`

export const PageButton = styled.button`
  font-size: 1.1em;
`

export const PageText = styled.div`
  margin-inline: 20px;
`
