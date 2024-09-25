package com.giveus.admin.domain.funding.application;

import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.funding.domain.FundingStatusHistory;

public interface FundingStatusHistoryService {
    FundingStatusHistory createHistory(Funding funding);

}
