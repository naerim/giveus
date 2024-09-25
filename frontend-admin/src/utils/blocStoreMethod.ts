import Web3 from 'web3'
import FundRaisingContract from '../assets/data/FundRaising.json'
import { BlocStoreType } from '@/types/fundingType'
import FundUsageContract from '../assets/data/FundUsage.json'

const ETHEREUM_RPC_URL = 'https://rpc.ssafy-blockchain.com'

// const privateKey = import.meta.env.VITE_ADMIN_PRIVKEY
const privateKey = 'ccde9fd1e91c71a46ebca973189dec872dfc408852dd2078244b7e5c61b5990c'

// const hospitalAddress = import.meta.env.VITE_ADMIN_ADDRESS
const hospitalAddress = '0x395950AD0039Df7232a37EE11FA92ec93F58F1fa'

// const FUND_REG_CA = import.meta.env.FUND_REG_CA
// const FUND_USG_CA = import.meta.env.VITE_FUND_USG_CA
const FUND_REG_CA = '0xeABe49609d969398Ef7D8e98C69A2ea964C762dc'
const FUND_USG_CA = '0xc32C0835B97E21429161295cB74aAa949Ea3BBea'

const web3 = new Web3(Web3.givenProvider || ETHEREUM_RPC_URL)

// 스마트 계약 인스턴스 생성
const FundRegCI = new web3.eth.Contract(
  FundRaisingContract.abi, // ABI
  FUND_REG_CA, // 스마트 계약 주소(CA)
)

const FundUsageCI = new web3.eth.Contract(
  FundUsageContract.abi, // ABI
  FUND_USG_CA, // 스마트 계약 주소(CA)
)

// 펀딩 정보 추가 
export const createFunding = async (props: BlocStoreType) => {
  const { goalAmount, startTime, endTime, title, hospitalName } = props
  try {
    const nonce = await web3.eth.getTransactionCount(hospitalAddress)
    const gasPrice = await web3.eth.getGasPrice() // 현재 가스가격

    // 트랜잭션 객체 생성
    const txObject = {
      nonce: web3.utils.toHex(nonce),
      gasPrice: gasPrice, // 송신자가 gas당 지불하고자 하는 비용
      gasLimit: 30000000, // 송신자가 트랜잭션 실행을 위해 지불할 용의가 있는 gas비용 최대치
      value: 0, // 송신자에서 수신자로 전송되는 금액(이더)
      from: hospitalAddress,
      to: FUND_REG_CA,
      data: FundRegCI.methods
        .createFunding(
          goalAmount,
          startTime,
          endTime,
          title,
          hospitalAddress,
          hospitalName,
        )
        .encodeABI(),
    }
    console.log(txObject)

    // 개인키 활용하여 트랜잭션 서명 : 트랜잭션 유효성 검증
    const signedTx = await web3.eth.accounts.signTransaction(
      txObject,
      privateKey,
    )
    console.log('서명된 트랜잭션 생성', signedTx)

    // 서명된 트랜잭션 전송
    const txReceipt = await web3.eth.sendSignedTransaction(
      signedTx.rawTransaction,
    )
    console.log('트랜잭션 전송 성공:', txReceipt)
  } catch (error) {
    console.error('Error creating funding:', error)
  }
}

// 펀딩 정보 조회
export const getFundingInfo = async () => {
  try {
    const index = 2 // 조회할 펀딩 인덱스 설정
    const result = await FundRegCI.methods.getFunding(index).call()
    console.log('펀딩 정보:', result)
  } catch (error) {
    console.error('Error fetching funding info:', error)
  }
}

// 모든 펀딩 정보 목록 조회
export const getAllFundings = async () => {
  try {
    const result = await FundRegCI.methods.getAllFundings().call()
    console.log('펀딩 목록:', result)
    // setFundings(result); // 펀딩 목록을 상태 변수에 저장
  } catch (error) {
    console.error('Error fetching all fundings:', error)
  }
}

// 기부자 정보 추가
export const addDonor = async (
  index: number,
  donorName: string,
  date: number,
  amount: number,
) => {
  try {
    const nonce = await web3.eth.getTransactionCount(hospitalAddress)
    const gasPrice = await web3.eth.getGasPrice()

    const txObject = {
      nonce: web3.utils.toHex(nonce),
      gasPrice: gasPrice,
      gasLimit: 30000000,
      value: 0,
      from: hospitalAddress,
      to: FUND_REG_CA,
      data: FundRegCI.methods
        .addDonation(index, donorName, date, amount)
        .encodeABI(),
    }

    const signedTx = await web3.eth.accounts.signTransaction(
      txObject,
      privateKey,
    )

    const txReceipt = await web3.eth.sendSignedTransaction(
      signedTx.rawTransaction,
    )
    console.log('트랜잭션 전송 성공:', txReceipt)
  } catch (error) {
    console.error('Error adding donor:', error)
  }
}

// 기금 사용 내역 추가 함수
export const addExpense = async (expenses: any) => {
  try {
    const nonce = await web3.eth.getTransactionCount(hospitalAddress)
    const gasPrice = await web3.eth.getGasPrice()

    const txObject = {
      nonce: web3.utils.toHex(nonce),
      gasPrice: gasPrice,
      gasLimit: 30000000,
      value: 0,
      from: hospitalAddress,
      to: FUND_USG_CA,
      data: FundUsageCI.methods.addExpense(expenses).encodeABI(),
    }

    const signedTx = await web3.eth.accounts.signTransaction(
      txObject,
      privateKey,
    )

    const txReceipt = await web3.eth.sendSignedTransaction(
      signedTx.rawTransaction,
    )
    console.log('기금 사용 내역 추가 트랜잭션 전송 성공:', txReceipt)
  } catch (error) {
    console.error('Error adding expenses:', error)
  }
}

// 기금 사용 내역 목록 반환
export const getExpensesList = async () => {
  try {
    const expensesList = await FundUsageCI.methods.getExpenseList().call()
    console.log('비용 목록 배열:', expensesList)
    return expensesList
  } catch (error) {
    console.error('Error fetching expense list array:', error)
    return []
  }
}

// 기부자 추가 버튼 클릭 시 호출되는 함수
export const handleAddDonor = async () => {
  const index = 0 // 펀딩 인덱스 설정 (원하는 값으로 변경)
  const donorName = '이하늬' // 추가할 기부자 이름 설정 (원하는 값으로 변경)
  const date = Date.now()
  const amount = 9500
  await addDonor(index, donorName, date, amount)
}
