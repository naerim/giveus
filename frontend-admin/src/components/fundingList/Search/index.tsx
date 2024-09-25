import { currentNavState } from '@/store/common'
import * as s from '@components/fundingList/Search/Search.styled'
import { useNavigate } from 'react-router-dom'
import { useSetRecoilState } from 'recoil'

const index = (props: { onSubmit: (filterValue: string) => void }) => {
  const { onSubmit } = props
  const setCurrentNav = useSetRecoilState(currentNavState)
  const navigate = useNavigate()

  const handleSubmit = (e: any) => {
    e.preventDefault()
    onSubmit(e.target.elements.filter.value)
  }

  const HandlerButton = () => {
    setCurrentNav({ name: '등록', url: '/fundingReg', label: '펀딩 등록' })
    navigate('/fundingReg')
  }

  return (
    <s.Container>
      <s.Form onSubmit={handleSubmit}>
        <s.Input name="filter" placeholder="검색어를 입력하세요." />
      </s.Form>

      <s.Button onClick={() => HandlerButton()}>펀딩 등록</s.Button>
    </s.Container>
  )
}

export default index
