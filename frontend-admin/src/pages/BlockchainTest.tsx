import Web3 from 'web3'
import FundRaisingContract from '../assets/data/FundRaising.json'
import FundUsageContract from '../assets/data/FundUsage.json'
import Button from '@/common/button/Button'
import { addExpense, getExpensesList } from '@/utils/blocStoreMethod'


const BlockchainTest = () => {
  const ETHEREUM_RPC_URL = 'https://rpc.ssafy-blockchain.com'

  const privateKey =
    'ccde9fd1e91c71a46ebca973189dec872dfc408852dd2078244b7e5c61b5990c'
  const hospitalAddress = '0x395950AD0039Df7232a37EE11FA92ec93F58F1fa'
  const FUND_REG_CA = '0x1bF1a205716603bddB5Af858f3df9120F371475f'
  // const FUND_USG_CA= '0x65E7cE002b0a8adCEc8AAa0D078c5E1Da153A993'


  // Web3.js를 사용하여 블록체인 네트워크와 연결..
  const web3 = new Web3(Web3.givenProvider || ETHEREUM_RPC_URL)

  // 스마트 계약 인스턴스 생성`
  const FundRegCI = new web3.eth.Contract(
    FundRaisingContract.abi, // ABI
    FUND_REG_CA, // 스마트 계약 주소(CA)
  )

  const FundUsageCI = new web3.eth.Contract(
    FundRaisingContract.abi, // ABI
    FUND_REG_CA, // 스마트 계약 주소(CA)
  )

  // createFunding 함수 호출
  const createFunding = async () => {
    try {
      // 펀딩 정보를 저장할 때 필요한 매개변수 설정
      const goalAmount = 45300000 // 예시: 목표 금액
      const startTime = '20240327'
      const endTime = '20240404'
      const title = '소아암 15세(여)' // 예시: 펀딩 제목
      const hospitalName = '싸피병원' // 병원 이름
      const nonce = await web3.eth.getTransactionCount(hospitalAddress)
      const gasPrice = await web3.eth.getGasPrice() // 현재 가스가격

      // 트랜잭션 객체 생성
      const txObject = {
        nonce: web3.utils.toHex(nonce),
        // gasPrice: web3.utils.toWei('0.01', 'gwei'), // 송신자가 gas당 지불하고자 하는 비용
        gasPrice: gasPrice, // 송신자가 gas당 지불하고자 하는 비용
        gasLimit: 30000000, // 송신자가 트랜잭션 실행을 위해 지불할 용의가 있는 gas비용 최대치
        value: 0, // 송신자에서 수신자로 전송되는 금액
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

  // 펀딩 정보 조회 함수 추가
  const getFundingInfo = async () => {
    try {
      const index = 1 // 조회할 펀딩 인덱스 설정
      const result = await FundRegCI.methods.getFunding(index).call()
      console.log('펀딩 정보:', result)
    } catch (error) {
      console.error('Error fetching funding info:', error)
    }
  }

  const getAllFundings = async () => {
    try {
      const result = await FundRegCI.methods.getAllFundings().call()
      console.log('펀딩 목록:', result)
      // setFundings(result); // 펀딩 목록을 상태 변수에 저장
    } catch (error) {
      console.error('Error fetching all fundings:', error)
    }
  }

  const addDonor = async (
    index: number,
    donorName: string,
    date: number,
    amount: number,
  ) => {
    try {
      // 함수를 호출할 때 필요한 트랜잭션 매개변수 설정
      const nonce = await web3.eth.getTransactionCount(hospitalAddress)
      const gasPrice = await web3.eth.getGasPrice()

      // 트랜잭션 객체 생성
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

      // 개인키를 사용하여 트랜잭션 서명
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
      console.error('Error adding donor:', error)
    }
  }

  // 기부자 추가 버튼 클릭 시 호출되는 함수
  const handleAddDonor = async () => {
    const index = 1 // 펀딩 인덱스 설정 (원하는 값으로 변경)
    const donorName = '이하늬' // 추가할 기부자 이름 설정 (원하는 값으로 변경)
    const date = Date.now()
    const amount = 950000
    await addDonor(index, donorName, date, amount)
  }

  const handleAddExpense = async () => {
    const newExpense = [
      ['검사료','2024-04-01','피검사',1,30000],
      ['화학치료','2024-04-02','약물치료',2,210000],
      ['수술','2024-04-03','종양제거',1,1000000],
      ['수술','2024-04-03','종양제거',1,1000000],
      ['수술','2024-04-03','종양제거',1,1000000],
    ]
    await addExpense(newExpense)
  }



  const handleGetExpLists = async () => {
    const res = await getExpensesList()
    console.log(res);
  }

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        gap: '40px',
      }}
    >
      <Button
        $backgroundColor={'blue'}
        width={'200px'}
        height={'100px'}
        $borderRadius={'10px'}
        $children={'펀딩 정보 저장'}
        onButtonClick={createFunding}
      ></Button>
      <Button
        $backgroundColor={'blue'}
        width={'200px'}
        height={'100px'}
        $borderRadius={'10px'}
        $children={'펀딩 데이터 조회'}
        onButtonClick={getFundingInfo}
      ></Button>
      <Button
        $backgroundColor={'blue'}
        width={'200px'}
        height={'100px'}
        $borderRadius={'10px'}
        $children={'기부자 추가'}
        onButtonClick={handleAddDonor}
      ></Button>
      <Button
        $backgroundColor={'blue'}
        width={'200px'}
        height={'100px'}
        $borderRadius={'10px'}
        $children={'펀딩 전체 정보 조회'}
        onButtonClick={getAllFundings}
      ></Button>
      <Button
        $backgroundColor={'blue'}
        width={'200px'}
        height={'100px'}
        $borderRadius={'10px'}
        $children={'기부금 사용 내역 추가'}
        onButtonClick={handleAddExpense}
      ></Button>
      <Button
        $backgroundColor={'blue'}
        width={'200px'}
        height={'100px'}
        $borderRadius={'10px'}
        $children={'기부금 사용 내역 조회들들'}
        onButtonClick={handleGetExpLists}
      ></Button>
    </div>
  )
}

export default BlockchainTest
