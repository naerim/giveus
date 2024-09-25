import * as l from '@components/login/LoginFooter/LoginFooter.styled'

const index = () => {
  return (
    <l.Container>
      <l.Wrap>
        <l.Copyright>
          @ 2024, Made with ❤️ by{' '}
          <l.Blue onClick={() => window.open('https://giveus.site', '_blank')}>
            GIVEUS Team
          </l.Blue>{' '}
          &{' '}
          <l.Blue onClick={() => window.open('https://ssafy.com', '_blank')}>
            SSAFY
          </l.Blue>{' '}
          for a better web
        </l.Copyright>
        <l.Menu>
          <l.Text>GIVEUS</l.Text>
          <l.Text>SSAFY</l.Text>
          <l.Text>C206</l.Text>
          <l.Text>License</l.Text>
        </l.Menu>
      </l.Wrap>
    </l.Container>
  )
}

export default index
