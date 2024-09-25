import * as h from '@components/home/HomeFooter/HomeFooter.styled'

const index = () => {
  return (
    <h.Container>
      <h.Wrap>
        <h.Copyright>
          @ 2024, Made with ❤️ by{' '}
          <h.Blue onClick={() => window.open('https://giveus.site', '_blank')}>
            GIVEUS Team
          </h.Blue>{' '}
          &{' '}
          <h.Blue onClick={() => window.open('https://ssafy.com', '_blank')}>
            SSAFY
          </h.Blue>{' '}
          for a better web
        </h.Copyright>
        <h.Menu>
          <h.Text>GIVEUS</h.Text>
          <h.Text>SSAFY</h.Text>
          <h.Text>C206</h.Text>
          <h.Text>License</h.Text>
        </h.Menu>
      </h.Wrap>
    </h.Container>
  )
}

export default index
