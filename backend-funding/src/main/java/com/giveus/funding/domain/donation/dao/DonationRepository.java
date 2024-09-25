package com.giveus.funding.domain.donation.dao;

import com.giveus.funding.domain.donation.domain.MemberFunding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<MemberFunding, Integer>, DonationRepositoryCustom {


}
