package com.giveus.funding.domain.funding.application;

import com.giveus.funding.domain.funding.dao.FundingDetailRepository;
import com.giveus.funding.domain.funding.dao.FundingRepository;
import com.giveus.funding.domain.funding.domain.Funding;
import com.giveus.funding.domain.funding.domain.FundingDetail;
import com.giveus.funding.domain.funding.dto.FundingCreateReq;
import com.giveus.funding.domain.funding.dto.FundingDetailRes;
import com.giveus.funding.domain.funding.dto.FundingListRes;
import com.giveus.funding.domain.funding.exception.AlreadyExistFundingException;
import com.giveus.funding.domain.funding.exception.FundingNotFoundException;
import com.giveus.funding.global.common.enums.Folder;
import com.giveus.funding.global.common.response.CreateSuccessDto;
import com.giveus.funding.global.error.exception.InvalidRequestDataException;
import com.giveus.funding.global.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepository;
    private final FundingDetailRepository fundingDetailRepository;
    private final FileUtil fileUtil;

    /**
     * @inheritDoc
     */
    @Override
    public List<FundingListRes> getFundingList(String sort, Integer size) {

        if (sort == null || size==null) {
            return fundingRepository.getFundingList();
        }
        return fundingRepository.getFundingListSortByEndDate(size);
    }


    /**
     * @inheritDoc
     */
    @Override
    @Cacheable(value = "fundingList", key = "'fundingNo:'+#fundingNo")
    public FundingDetailRes getFunding(Integer fundingNo) {
        return fundingRepository.getFunding(fundingNo)
                .orElseThrow(FundingNotFoundException::new);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Funding getFundingEntity(int fundingNo) {
        return fundingRepository.findFundingByFundingNo(fundingNo)
                .orElseThrow(FundingNotFoundException::new);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Funding getFundingEntity(String reqId) {
        return fundingRepository.findFundingByRegId(reqId)
                .orElseThrow(FundingNotFoundException::new);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public CreateSuccessDto createFunding(FundingCreateReq fundingCreateReq, MultipartFile file) {

        // 펀딩마다 고유하게 갖고있는 regId로 펀딩 가져오기
        Funding funding = getFundingEntity(fundingCreateReq.getRegId());

        // 해당 펀딩에 대해 이미 등록되어있는 펀딩인지 확인
        if (isExistFundingDetail(funding)) { // 이미 등록되어있는 펀딩이라면 409 예외 발생
            throw new AlreadyExistFundingException();
        }

        // 펀딩 2차 등록
        FundingDetail fundingDetail = new FundingDetail();
        fundingDetail.setFunding(funding);
        if (fundingCreateReq.getContent() != null) { // 소개글이 있을 경우
            fundingDetail.setContent(fundingCreateReq.getContent());
        }
        String objectName = null;
        if (Objects.nonNull(file)) { // 업로드 할 파일이 있을 경우
            objectName = fileUtil.generateObjectName(); // 랜덤 오브젝트명 생성
            String url = fileUtil.upload(file, objectName, Folder.FUNDING); // 파일 업로드
            fundingDetail.setThumbnailUrl(url);
        }

        try { // 등록 시도
            fundingDetailRepository.save(fundingDetail);
        } catch (Exception e) { // 등록 실패 시
            if (Objects.nonNull(file)) { // 업로드 한 파일이 있을 경우
                // TODO 더 테스트가 필요할듯
                fileUtil.delete(objectName, Folder.FUNDING); // AWS S3에서 등록했던 파일 삭제
            }
            throw new InvalidRequestDataException("등록 요청 데이터 형식이 맞지 않습니다.");
        }

        return new CreateSuccessDto(funding.getFundingNo());
    }


    /**
     * @inheritDoc
     */
    @Override
    public List<FundingListRes> getFundingSearchList(String query) {
        return fundingRepository.getFundingByFundingTitle(query);
    }


    @Override
    public List<FundingListRes> getFundingListSortByEndDate(Integer limit) {
        return fundingRepository.getFundingListSortByEndDate(limit);
    }


    /**
     * 펀딩 추가 정보의 등록 여부를 확인하는 메서드입니다.
     *
     * @param funding 조회할 펀딩
     * @return 이미 등록되어 있으면 true 반환, 등록되어 있지 않으면 false 반환
     */
    private boolean isExistFundingDetail(Funding funding) {
        Optional<FundingDetail> fundingDetail =
                fundingDetailRepository.findFundingDetailByFunding(funding);
        return fundingDetail.isPresent();
    }

}
