package com.giveus.payment.repository;

import com.giveus.payment.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<Funding, Integer> {
}
