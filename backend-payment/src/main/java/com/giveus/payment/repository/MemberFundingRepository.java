package com.giveus.payment.repository;

import com.giveus.payment.entity.MemberFunding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFundingRepository extends JpaRepository<MemberFunding, Integer>, MemberFundingRepositoryCustom {
}
