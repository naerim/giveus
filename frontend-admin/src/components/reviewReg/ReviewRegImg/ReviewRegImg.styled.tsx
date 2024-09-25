import styled from 'styled-components'

export const Container = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 25%;
  background-color: #fff;
  margin: 5%;
  border-radius: 12px;
  row-gap: 5%;
`

export const PreviewImage = styled.img`
  position: absolute;
  width: 90%;
  height: 90%;
  margin: auto;
  border-radius: 8px;
  cursor: pointer;
`

export const Desc = styled.div`
  font-size: 1.2em;
  font-weight: 600;
`

export const FileInput = styled.input`
  width: 0;
  height: 0;
  margin: 0;
  overflow: hidden;
`

export const Button = styled.button`
  background-color: #D8EAFF;
  border-radius: 50%;
  padding: 3%;
`

export const Icon = styled.img`
  width: 24px;
  height: 24px;`