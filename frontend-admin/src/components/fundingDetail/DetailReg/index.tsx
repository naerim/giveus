import Modal from '@/common/Modal'
import * as d from '@/components/fundingDetail/DetailReg/DetailReg.styled'
import { fundingDetailState } from '@/store/funding'
import { useState } from 'react'
import { useRecoilValue } from 'recoil'
import UsageReg from '@components/fundingDetail/UsageReg'

const index = () => {
  const fundingDetail = useRecoilValue(fundingDetailState)
  const [open, setOpen] = useState<boolean>(false)

  return (
    <>
      <d.Container>
        <d.Wrap>
          <d.ImgBox>
            <d.Img
              src={
                fundingDetail.thumbnailUrl || '/icon/icon_default_profile.png'
              }
            />
          </d.ImgBox>
          <d.Title>{fundingDetail.title}</d.Title>
        </d.Wrap>
        <d.Button
          $isDisabled={fundingDetail.isRegUsage}
          disabled={fundingDetail.isRegUsage}
          onClick={() => setOpen(true)}
        >
          {fundingDetail.isRegUsage
            ? '기금 사용 내역 등록 완료'
            : '기금 사용 내역 등록'}
        </d.Button>
      </d.Container>
      {open && (
        <Modal name="기금 사용 내역 등록" onClose={() => setOpen(false)}>
          <UsageReg value={open} setValue={setOpen} />
        </Modal>
      )}
    </>
  )
}

export default index
