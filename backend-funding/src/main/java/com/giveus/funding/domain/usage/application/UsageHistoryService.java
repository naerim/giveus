package com.giveus.funding.domain.usage.application;

import com.giveus.funding.domain.usage.dto.FundingUsageListRes;

import java.util.List;

public interface UsageHistoryService {
    /**
     * 펀딩 후원금 사용 내역을 조회하는 메서드입니다.
     * 
     * @param fundingNo 조회할 펀딩 고유 번호
     * @return 펀딩 사용 내역 목록
     */
    List<FundingUsageListRes> getUsageList(int fundingNo);

}
