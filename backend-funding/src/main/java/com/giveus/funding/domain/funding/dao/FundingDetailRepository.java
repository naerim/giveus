package com.giveus.funding.domain.funding.dao;

import com.giveus.funding.domain.funding.domain.Funding;
import com.giveus.funding.domain.funding.domain.FundingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundingDetailRepository extends JpaRepository<FundingDetail, Integer> {

    Optional<FundingDetail> findFundingDetailByFunding(Funding funding);

}
