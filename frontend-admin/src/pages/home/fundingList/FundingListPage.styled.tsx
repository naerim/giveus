import styled from 'styled-components'

export const Container = styled.div`
  position: relative;
  margin: 1% 3%;
  padding: 1% 2%;
  background-color: #fff;
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
