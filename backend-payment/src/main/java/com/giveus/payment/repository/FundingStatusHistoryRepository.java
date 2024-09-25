package com.giveus.payment.repository;

import com.giveus.payment.entity.FundingStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingStatusHistoryRepository extends JpaRepository<FundingStatusHistory, Integer>, FundingStatusHistoryRepositoryCustom {
}
