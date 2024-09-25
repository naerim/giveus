package com.giveus.payment.repository;

import com.giveus.payment.entity.PointUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUsageRepository extends JpaRepository<PointUsage, Integer>, PointUsageRepositoryCustom {
}
