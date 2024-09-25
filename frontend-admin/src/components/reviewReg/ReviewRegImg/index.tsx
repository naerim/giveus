import { FileStateType } from '@/types/commonType'
import * as r from '@components/reviewReg/ReviewRegImg/ReviewRegImg.styled'
import { useRef, useState } from 'react'

const index = (props: FileStateType) => {
  const { setValue } = props
  const fileInput = useRef<HTMLInputElement | null>(null)
  const [previewImage, setPreviewImage] = useState<string | null>(null)

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0]
    if (selectedFile) {
      setValue(selectedFile)
      const reader = new FileReader()
      reader.onloadend = () => {
        setPreviewImage(reader.result as string)
      }
      reader.readAsDataURL(selectedFile)
    }
  }

  return (
    <r.Container>
      <r.FileInput
        type="file"
        accept="image/*"
        ref={fileInput}
        onChange={handleFileChange}
      />
      {previewImage ? (
        <r.PreviewImage
          src={previewImage}
          alt="Preview"
          onClick={() => fileInput.current?.click()}
        />
      ) : (
        <>
          <r.Desc>썸네일을 추가해주세요</r.Desc>
          <r.Button>
            <r.Icon
              src="/icon/icon_plus_blue.png"
              alt=""
              onClick={() => fileInput.current?.click()}
            />
          </r.Button>
        </>
      )}
    </r.Container>
  )
}

export default index
