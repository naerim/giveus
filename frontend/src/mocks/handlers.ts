import { http, HttpResponse } from 'msw'
import { contributors } from '@mocks/dummy.ts'

export const handlers = [
  http.get('/api/participants', () => {
    return HttpResponse.json(contributors)
  }),
]
