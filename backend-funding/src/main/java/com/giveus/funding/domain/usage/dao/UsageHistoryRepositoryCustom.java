package com.giveus.funding.domain.usage.dao;

import com.giveus.funding.domain.usage.dto.FundingUsageListRes;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface UsageHistoryRepositoryCustom {
    List<FundingUsageListRes> getUsageList(int fundingNo);

}
