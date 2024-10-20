import ReactDOM from 'react-dom/client'
import { RecoilRoot } from 'recoil'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { HelmetProvider } from 'react-helmet-async'
import App from './App'

// production mode에서 주석 제거
// if (process.env.NODE_ENV === 'production') {
//   console = window.console || {}
//   console.log = function no_console() {}
//   console.warn = function no_console() {}
//   console.error = function () {}

async function enableMocking() {
  // if (!import.meta.env.DEV) {
  //   return
  // }
  const { worker } = await import('./mocks/browser.ts')
  return worker.start({
    onUnhandledRequest: (request, print) => {
      if (!request.url.includes('/api/')) {
        console.log('/api/ 가 포함되지 않은 요청 url', request.url)
        return
      }
      print.warning()
    },
  })
}

const queryClient = new QueryClient()
const helmetContext = {}

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById('root')!).render(
    <HelmetProvider context={helmetContext}>
      <RecoilRoot>
        <QueryClientProvider client={queryClient}>
          <App />
        </QueryClientProvider>
      </RecoilRoot>
    </HelmetProvider>,
  )
})
