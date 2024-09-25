import ReviewRegContent from '@/components/reviewReg/ReviewRegContent'
import ReviewRegImg from '@/components/reviewReg/ReviewRegImg'
import FullButton from '@/common/Fullbutton'
import * as r from '@pages/reviewReg/ReviewRegPage.styled'
import { useMutation } from '@tanstack/react-query'
import { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import HomeHeader from '@/common/HomeHeader'
import { createReview } from '@/apis/funding'

const ReviewRegPage = () => {
  const navigate = useNavigate()
  const [text, setText] = useState('')
  const [file, setFile] = useState<File | null>(null)
  const { id } = useParams()

  const { mutate } = useMutation({
    mutationKey: ['createReview'],
    mutationFn: createReview,
    onSuccess(result) {
      console.log('등록 성공', result)
      navigate('/giveus/review-ok', { replace: true })
    },
    onError(error) {
      console.error('등록 실패:', error)
      alert(error)
    },
  })

  const handleCreateSecondReg = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    if (file) {
      const formData = new FormData()

      formData.append('file', file)

      formData.append(
        'reviewCreateReq',
        new Blob(
          [
            JSON.stringify({
              regId: id,
              title: '기부해 주셔서 감사합니다',
              content: text,
            }),
          ],
          {
            type: 'application/json',
          },
        ),
      )

      mutate(formData)
    }
  }

  return (
    <>
      <r.Container>
        <HomeHeader />
        <r.Title>후기 등록</r.Title>
        <ReviewRegImg value={file} setValue={setFile} />
        <ReviewRegContent value={text} setValue={setText} />
        <r.Form onSubmit={handleCreateSecondReg} encType="multipart/form-data">
          <FullButton text="후기 등록" disabled={false} />
        </r.Form>
      </r.Container>
    </>
  )
}

export default ReviewRegPage
