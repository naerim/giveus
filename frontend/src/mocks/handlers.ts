import { http, HttpResponse } from 'msw'
import { contributors, homeReviews, soonOverFunding } from '@mocks/dummy.ts'

export const handlers = [
  http.get('/api/v1/funding/participants', () => {
    return HttpResponse.json(contributors)
  }),
  http.get('/api/v1/funding/total-amount', () => {
    return HttpResponse.json({ totalAmount: 100000000 })
  }),
  http.get('/api/v1/review/findAll/3', () => {
    return HttpResponse.json(homeReviews)
  }),
  http.get('/api/v1/funding/soon-over?limit=3', () => {
    return HttpResponse.json(soonOverFunding)
  }),
]
