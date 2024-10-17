import { http, HttpResponse } from 'msw'
import { contributors } from '@mocks/dummy.ts'

export const handlers = [
  http.get('/api/v1/funding/participants', () => {
    return HttpResponse.json(contributors)
  }),
]
