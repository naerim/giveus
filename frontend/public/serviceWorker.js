// // FCM 관련 코드
// importScripts(
//   'https://www.gstatic.com/firebasejs/10.8.0/firebase-app-compat.js',
// )
// importScripts(
//   'https://www.gstatic.com/firebasejs/10.8.0/firebase-messaging-compat.js',
// )
//
// // FCM 초기화
// firebase.initializeApp({
//   apiKey: 'AIzaSyDYateUNVpKGxzX6cDWZMFNpgvL88F5X4w',
//   authDomain: 'give-us-7b772.firebaseapp.com',
//   projectId: 'give-us-7b772',
//   storageBucket: 'give-us-7b772.appspot.com',
//   messagingSenderId: '275937553728',
//   appId: '1:275937553728:web:d36e5c4d883d18ffb91ea5',
//   measurementId: 'G-DMS5D5QMFN',
// })
//
// const messaging = firebase.messaging()
//
// // FCM 푸시 알림 수신 처리
// self.addEventListener('push', function (e) {
//   const notification = e.data.json().notification
//
//   const notificationTitle = notification.title
//   const notificationOptions = {
//     body: notification.body,
//     icon: notification.icon,
//   }
//
//   if (notification && notificationTitle && notificationOptions.body) {
//     self.registration.showNotification(notificationTitle, notificationOptions)
//   }
// })
//
// // FCM 알림 클릭 이벤트 처리
// self.addEventListener('notificationclick', function (e) {
//   e.notification.close()
//   e.waitUntil(clients.openWindow('/alarm'))
// })
//
// // MSW 관련 코드
//
// const PACKAGE_VERSION = '2.4.9'
// const INTEGRITY_CHECKSUM = '26357c79639bfa20d64c0efca2a87423'
// const IS_MOCKED_RESPONSE = Symbol('isMockedResponse')
// const activeClientIds = new Set()
//
// // 서비스 워커 설치
// self.addEventListener('install', function () {
//   self.skipWaiting()
//   console.log('[Service Worker] installed')
// })
//
// // 서비스 워커 활성화
// self.addEventListener('activate', function (event) {
//   event.waitUntil(self.clients.claim())
//   console.log('Service Worker activated')
// })
//
// // MSW 메시지 처리
// self.addEventListener('message', async function (event) {
//   const clientId = event.source.id
//
//   if (!clientId || !self.clients) {
//     return
//   }
//
//   const client = await self.clients.get(clientId)
//
//   if (!client) {
//     return
//   }
//
//   const allClients = await self.clients.matchAll({
//     type: 'window',
//   })
//
//   switch (event.data) {
//     case 'KEEPALIVE_REQUEST': {
//       sendToClient(client, {
//         type: 'KEEPALIVE_RESPONSE',
//       })
//       break
//     }
//
//     case 'INTEGRITY_CHECK_REQUEST': {
//       sendToClient(client, {
//         type: 'INTEGRITY_CHECK_RESPONSE',
//         payload: {
//           packageVersion: PACKAGE_VERSION,
//           checksum: INTEGRITY_CHECKSUM,
//         },
//       })
//       break
//     }
//
//     case 'MOCK_ACTIVATE': {
//       activeClientIds.add(clientId)
//
//       sendToClient(client, {
//         type: 'MOCKING_ENABLED',
//         payload: true,
//       })
//       break
//     }
//
//     case 'MOCK_DEACTIVATE': {
//       activeClientIds.delete(clientId)
//       break
//     }
//
//     case 'CLIENT_CLOSED': {
//       activeClientIds.delete(clientId)
//
//       const remainingClients = allClients.filter(client => {
//         return client.id !== clientId
//       })
//
//       // 클라이언트가 남아 있지 않으면 서비스 워커 해제
//       if (remainingClients.length === 0) {
//         self.registration.unregister()
//       }
//
//       break
//     }
//   }
// })
//
// // 네트워크 요청 가로채기 (MSW에서 사용)
// self.addEventListener('fetch', function (event) {
//   const { request } = event
//
//   // 탐색 요청 무시
//   if (request.mode === 'navigate') {
//     return
//   }
//
//   // "only-if-cached" 요청은 처리하지 않음
//   if (request.cache === 'only-if-cached' && request.mode !== 'same-origin') {
//     return
//   }
//
//   // 활성화된 클라이언트가 없으면 요청 무시
//   if (activeClientIds.size === 0) {
//     return
//   }
//
//   // 요청 ID 생성
//   const requestId = crypto.randomUUID()
//   event.respondWith(handleRequest(event, requestId))
// })
//
// // 요청 처리 함수
// async function handleRequest(event, requestId) {
//   const client = await resolveMainClient(event)
//   const response = await getResponse(event, client, requestId)
//
//   if (client && activeClientIds.has(client.id)) {
//     ;(async function () {
//       const responseClone = response.clone()
//
//       sendToClient(
//         client,
//         {
//           type: 'RESPONSE',
//           payload: {
//             requestId,
//             isMockedResponse: IS_MOCKED_RESPONSE in response,
//             type: responseClone.type,
//             status: responseClone.status,
//             statusText: responseClone.statusText,
//             body: responseClone.body,
//             headers: Object.fromEntries(responseClone.headers.entries()),
//           },
//         },
//         [responseClone.body],
//       )
//     })()
//   }
//
//   return response
// }
//
// // 클라이언트 확인 함수
// async function resolveMainClient(event) {
//   const client = await self.clients.get(event.clientId)
//
//   if (client?.frameType === 'top-level') {
//     return client
//   }
//
//   const allClients = await self.clients.matchAll({
//     type: 'window',
//   })
//
//   return allClients
//     .filter(client => {
//       return client.visibilityState === 'visible'
//     })
//     .find(client => {
//       return activeClientIds.has(client.id)
//     })
// }
//
// // 클라이언트로 메시지 전송
// function sendToClient(client, message, transferrables = []) {
//   return new Promise((resolve, reject) => {
//     const channel = new MessageChannel()
//
//     channel.port1.onmessage = event => {
//       if (event.data && event.data.error) {
//         return reject(event.data.error)
//       }
//
//       resolve(event.data)
//     }
//
//     client.postMessage(
//       message,
//       [channel.port2].concat(transferrables.filter(Boolean)),
//     )
//   })
// }
//
// // 가짜 응답 처리
// async function getResponse(event, client, requestId) {
//   const { request } = event
//   const requestClone = request.clone()
//
//   function passthrough() {
//     const headers = Object.fromEntries(requestClone.headers.entries())
//     delete headers['x-msw-intention']
//     return fetch(requestClone, { headers })
//   }
//
//   if (!client) {
//     return passthrough()
//   }
//
//   if (!activeClientIds.has(client.id)) {
//     return passthrough()
//   }
//
//   const requestBuffer = await request.arrayBuffer()
//   const clientMessage = await sendToClient(
//     client,
//     {
//       type: 'REQUEST',
//       payload: {
//         id: requestId,
//         url: request.url,
//         mode: request.mode,
//         method: request.method,
//         headers: Object.fromEntries(request.headers.entries()),
//         body: requestBuffer,
//       },
//     },
//     [requestBuffer],
//   )
//
//   switch (clientMessage.type) {
//     case 'MOCK_RESPONSE': {
//       return respondWithMock(clientMessage.data)
//     }
//
//     case 'PASSTHROUGH': {
//       return passthrough()
//     }
//   }
//
//   return passthrough()
// }
//
// // 모킹 응답 반환
// async function respondWithMock(response) {
//   if (response.status === 0) {
//     return Response.error()
//   }
//
//   const mockedResponse = new Response(response.body, response)
//   Reflect.defineProperty(mockedResponse, IS_MOCKED_RESPONSE, {
//     value: true,
//     enumerable: true,
//   })
//
//   return mockedResponse
// }
