package com.giveus.funding.domain.funding.dao;

import com.giveus.funding.domain.funding.dto.FundingDetailRes;
import com.giveus.funding.domain.funding.dto.FundingListRes;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * @author 이하늬
 * @since 1.0
 */
@NoRepositoryBean
public interface FundingRepositoryCustom {
    List<FundingListRes> getFundingList();

    Optional<FundingDetailRes> getFunding(int fundingNo);

    List<FundingListRes> getFundingByFundingTitle(String query);

    List<FundingListRes> getFundingListSortByEndDate(Integer limit);
}
