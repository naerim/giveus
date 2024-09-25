package com.giveus.admin.domain.usage.application;

import com.giveus.admin.global.common.response.CreateSuccessDto;
import com.giveus.admin.domain.usage.dto.FundingUsageCreateReq;

import java.util.List;

public interface UsageHistoryService {
    CreateSuccessDto createFundingUsage(List<FundingUsageCreateReq> req);
}
