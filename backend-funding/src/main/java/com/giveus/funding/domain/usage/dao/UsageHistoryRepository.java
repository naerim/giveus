package com.giveus.funding.domain.usage.dao;

import com.giveus.funding.domain.usage.dto.FundingUsageListRes;
import com.giveus.funding.domain.usage.domain.UsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Integer>, UsageHistoryRepositoryCustom {
    List<FundingUsageListRes> findByFunding_FundingNoOrderByUsageHistoryNoDesc(int fundingNo);



}
