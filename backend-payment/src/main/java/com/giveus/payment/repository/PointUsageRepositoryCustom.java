package com.giveus.payment.repository;

import com.giveus.payment.dto.response.PointUsageRes;

import java.util.List;

public interface PointUsageRepositoryCustom {

    List<PointUsageRes> getUsageList(int memberNo);

}
