package com.giveus.admin.domain.usage.application;

import com.giveus.admin.domain.usage.dto.FundingUsageCreateReq;
import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.usage.domain.UsageHistory;

public class UsageHistoryTransfer {
    public static UsageHistory dtoToEntity(Funding funding, FundingUsageCreateReq req) {
        return UsageHistory.builder()
                .funding(funding)
                .count(req.getCount())
                .amount(req.getAmount())
                .content(req.getContent())
                .category(req.getCategory())
                .build();

    }

}
