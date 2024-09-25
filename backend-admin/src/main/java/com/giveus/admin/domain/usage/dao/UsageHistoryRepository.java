package com.giveus.admin.domain.usage.dao;

import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.usage.domain.UsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Integer> {

    List<UsageHistory> findByFunding(Funding funding);
}
