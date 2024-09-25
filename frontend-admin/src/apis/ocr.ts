// import * as FormData from 'form-data';
// const FormData = require('form-data');
import axios from 'axios'

const uuid = crypto.randomUUID()
const FundingRegInvokeUrl =
  'https://76mjhq6gjw.apigw.ntruss.com/custom/v1/29548/b76957781f2da1d145048bfe53cec9dff5380d89f1a338f6e71daad7f674490a/infer' // APIGW Invoke URL
const FundingRegSecretKey = 'WWVnTllobkZ1Z2lYQ3N4YmRjRlpHblpaeUhqSVhtdG4='

const UsageInvokeUrl = 'https://76mjhq6gjw.apigw.ntruss.com/custom/v1/29740/569f8173f1d095d58faa4de0c45fc36ee3fccf3591757e8b55dca1d287a5fbe4/infer'
const UsageSecretKey = 'ZnBhamhua1NnaE1Id1JTaHFueENVTVZ4aGpnWERRaEo='


export async function fundRegRequest(base64Data: string) {
  const message = {
    images: [
      {
        format: 'png',
        name: 'diagnosis',
        data: base64Data,
        templateIds: [28812],
      },
    ],
    requestId: uuid, // 고유한 문자열 생성
    timestamp: Date.now(),
    version: 'V2',
  }

  // Body에 json으로 변경 후 전송
  const jsonMessage = JSON.stringify(message)

  try {
    const response = await axios.post(FundingRegInvokeUrl, jsonMessage, {
      headers: {
        'X-OCR-SECRET': FundingRegSecretKey,
        'Content-Type': 'application/json',
      },
    });

    if (response.status === 200) {
      console.log('requestWithBase64 response:', response.data.images[0].fields);
      return response.data.images[0].fields;
    }
  } catch (err:any) {
    console.warn('requestWithBase64 error', err.response);
    throw err; // 에러를 다시 던져 호출한 쪽에서 처리할 수 있도록 함
  }
}




export async function usageRequest(base64Data: string) {
  const message = {
    images: [
      {
        format: 'png',
        name: 'usage',
        data: base64Data,
        // templateIds: [28812],
      },
    ],
    requestId: uuid, // 고유한 문자열 생성
    timestamp: Date.now(),
    version: 'V2',
  }

  // Body에 json으로 변경 후 전송
  const jsonMessage = JSON.stringify(message)

  try {
    const response = await axios.post(UsageInvokeUrl, jsonMessage, {
      headers: {
        'X-OCR-SECRET': UsageSecretKey,
        'Content-Type': 'application/json',
      },
    });

    if (response.status === 200) {
      console.log('requestWithBase64 response:', response.data.images[0].fields);
      return response.data.images[0].fields;
    }
  } catch (err:any) {
    console.warn('requestWithBase64 error', err.response);
    throw err; // 에러를 다시 던져 호출한 쪽에서 처리할 수 있도록 함
  }
}
