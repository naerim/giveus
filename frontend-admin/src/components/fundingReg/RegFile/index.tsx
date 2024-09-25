import * as r from '@/components/fundingReg/RegFile/RegFile.styled'
import { fundRegRequest } from '@/apis/ocr'
import { useCallback, useRef, useState } from 'react'
import { RegFileType } from '@/types/fundingType'

const index = ({ onOCRResult }: RegFileType) => {
  const fileInput = useRef<HTMLInputElement | null>(null)
  const [previewImage, setPreviewImage] = useState<string | null>(null)

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0]
    if (selectedFile) {
      const reader = new FileReader()
      reader.onloadend = () => {
        setPreviewImage(reader.result as string)
      }
      reader.readAsDataURL(selectedFile)
    }
  }

  const onRequestOCR = useCallback(async () => {
    if (previewImage) {
      const base64Data = previewImage.split(',')[1]
      try {
        const response = await fundRegRequest(base64Data)
        onOCRResult(response)
      } catch (err) {
        console.error('OCR 요청 실패', err)
      }
    } else {
      alert('이미지 파일을 먼저 업로드 해주세요')
    }
  }, [previewImage, onOCRResult])

  return (
    <r.Container>
      <r.Box>
        <r.Wrap onClick={() => fileInput.current?.click()}>
          {previewImage ? (
            <r.PreviewImage src={previewImage} alt="Preview" />
          ) : (
            <>
              <r.Icon src="/icon/icon_reg_file.png" />
              <r.Text>
                진단서 이미지 파일을
                <br />
                업로드 해주세요
              </r.Text>
            </>
          )}
        </r.Wrap>
      </r.Box>
      <r.Input
        type="file"
        accept="image/*"
        ref={fileInput}
        onChange={handleFileChange}
      />
      <r.BtnLayout>
        <r.BlueButton onClick={() => fileInput.current?.click()}>
          파일 선택
        </r.BlueButton>
        <r.OrangeButton onClick={onRequestOCR} disabled={false}>
          OCR 검사
        </r.OrangeButton>
      </r.BtnLayout>
    </r.Container>
  )
}

export default index
