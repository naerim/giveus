package com.giveus.admin.domain.funding.dao;

import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.funding.domain.FundingStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingStatusHistoryRepository extends JpaRepository<FundingStatusHistory, Integer> {
    List<FundingStatusHistory> findDistinctByFundingOrderByCreatedAtDesc(Funding funding);
}
