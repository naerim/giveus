package com.giveus.payment.repository;

import com.giveus.payment.entity.PointRecharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRechargeRepository extends JpaRepository<PointRecharge, Integer>, PointRechargeRepositoryCustom {
}
