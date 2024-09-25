import * as d from '@/components/fundingDetail/DetailFile/DetailFile.styled'
import { usageRequest } from '@/apis/ocr'
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
        const response = await usageRequest(base64Data)
        onOCRResult(response)
      } catch (err) {
        console.error('OCR 요청 실패', err)
      }
    } else {
      alert('이미지 파일을 먼저 업로드 해주세요')
    }
  }, [previewImage, onOCRResult])

  return (
    <d.Container>
      <d.Box>
        <d.Wrap onClick={() => fileInput.current?.click()}>
          {previewImage ? (
            <d.PreviewImage src={previewImage} alt="Preview" />
          ) : (
            <>
              <d.Icon src="/icon/icon_reg_file.png" />
              <d.Text>
                진단서 이미지 파일을 <br />
                업로드 해주세요
              </d.Text>
            </>
          )}
        </d.Wrap>
      </d.Box>
      <d.Input
        type="file"
        accept="image/*"
        ref={fileInput}
        onChange={handleFileChange}
      />
      <d.BtnLayout>
        <d.BlueButton onClick={() => fileInput.current?.click()}>
          파일 선택
        </d.BlueButton>
        <d.OrangeButton onClick={onRequestOCR} disabled={false}>
          OCR 검사
        </d.OrangeButton>
      </d.BtnLayout>
    </d.Container>
  )
}

export default index
