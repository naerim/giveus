import { createGlobalStyle } from 'styled-components'
import reset from 'styled-reset'

export const GlobalStyle = createGlobalStyle`
    ${reset}
    :root {
        font-family: 'Pretendard';
        font-size: 16px;
    }
    * {
        padding: 0;
        box-sizing: border-box;
    }
    html, body {
        width: 100%;
        height: 100vh;
        margin: 0 auto;
        padding: 0;
        font-weight: 400;
        line-height: 1.1;
        max-width: 1600px;
    }
    a {
        outline: none;
        color: inherit;
        text-decoration: none;
        cursor: pointer;
    }
    button {
        border: none;
        cursor: pointer;
        background: none;
    }
`
