package com.giveus.admin.domain.funding.dao;

import com.giveus.admin.domain.funding.domain.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingRepository extends JpaRepository<Funding, Integer>, FundingRepositoryCustom {
    Optional<Funding> findFundingByFundingNo(int fundingNo);
}
