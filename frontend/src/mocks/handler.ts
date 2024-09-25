import { http } from 'msw'

const mockData = ['test1', 'test2', 'test3']

// export const handler = [
//   http.get('/test', (_req, res, ctx) => {
//     return res(ctx.status(200), ctx.json(mockData))
//   }),
// ]

export const handler = [
  http.get('/test', () => {
    return new Response(JSON.stringify(mockData))
  }),
]
