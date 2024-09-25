package com.giveus.admin.domain.funding.application;

import com.giveus.admin.global.common.response.CreateSuccessDto;
import com.giveus.admin.domain.funding.dto.FundingCreateReq;
import com.giveus.admin.domain.funding.dto.FundingDetailsRes;
import com.giveus.admin.domain.funding.dto.FundingListRes;
import com.giveus.admin.domain.funding.domain.Funding;

import java.util.List;

public interface FundingService {
    CreateSuccessDto createFunding(FundingCreateReq fundingCreateReq);

    List<FundingListRes> getFundingList(int adminNo);

    Funding getFundingEntity(int fundingNo);

    FundingDetailsRes getFunding(int fundingNo);

    boolean isDoneFunding(Funding funding);

}
