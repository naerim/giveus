package com.giveus.funding.domain.funding.application;

import com.giveus.funding.domain.funding.domain.Funding;
import com.giveus.funding.domain.funding.dto.FundingCreateReq;
import com.giveus.funding.domain.funding.dto.FundingDetailRes;
import com.giveus.funding.domain.funding.dto.FundingListRes;
import com.giveus.funding.global.common.response.CreateSuccessDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FundingService {
    /**
     * 펀딩 목록을 조회하는 메서드입니다.
     *
     * @param sort 정렬 기준
     * @param size 조회할 갯수
     * @return 조회한 펀딩 목록
     */
    List<FundingListRes> getFundingList(String sort, Integer size);

    /**
     * 펀딩 번호로 펀딩 상세 조회하는 메서드입니다.
     *
     * @param fundingNo 조회할 펀딩 고유 번호
     * @return 조회한 펀딩 상세 정보
     */
    FundingDetailRes getFunding(Integer fundingNo);

    /**
     * 펀딩 번호로 펀딩 엔터티를 조회하는 메서드입니다.
     *
     * @param fundingNo 조회할 펀딩 고유 번호
     * @return 조회한 펀딩 엔터티
     */
    Funding getFundingEntity(int fundingNo);

    /**
     * 펀딩 추가정보에 필요한 고유 ID로 해당되는 펀딩을 조회하는 메서드입니다.
     *
     * @param regId 펀딩 추가정보 등록 고유 ID
     * @return 해당되는 펀딩 엔터티
     */
    Funding getFundingEntity(String regId);

    /**
     * 펀딩 2차 등록을 하는 메서드입니다.
     *
     * @param fundingCreateReq 등록할 펀딩 정보
     * @param file             펀딩 썸네일 파일
     * @return 등록한 펀딩의 고유 번호
     */
    CreateSuccessDto createFunding(FundingCreateReq fundingCreateReq, MultipartFile file);


    /**
     * 펀딩명으로 펀딩 검색 결과를 조회하는 메서드입니다.
     *
     * @param query 검색어 (검색할 펀딩명)
     * @return 검색 결과 (펀딩 목록)
     */
    List<FundingListRes> getFundingSearchList(String query);


    /**
     * 종료일이 가까운 순의 펀딩 전체 목록을 조회하는 메서드입니다.
     *
     * @param limit 조회할 펀딩 목록 갯수
     * @return 곧 종료되는 펀딩 목록
     */
    List<FundingListRes> getFundingListSortByEndDate(Integer limit);
}
