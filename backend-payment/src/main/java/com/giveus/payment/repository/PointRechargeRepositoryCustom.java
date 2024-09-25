package com.giveus.payment.repository;

import com.giveus.payment.dto.response.PointRechargeRes;

import java.util.List;

public interface PointRechargeRepositoryCustom {

    List<PointRechargeRes> getRechargeList(int memberNo);
}
