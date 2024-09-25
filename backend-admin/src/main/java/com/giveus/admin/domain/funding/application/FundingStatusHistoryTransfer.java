package com.giveus.admin.domain.funding.application;

import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.funding.domain.FundingStatusHistory;
import com.giveus.admin.domain.funding.domain.FundingStatus;

public class FundingStatusHistoryTransfer {
    public static FundingStatusHistory dtoToEntityCreated(Funding funding) {
        FundingStatusHistory fundingStatusHistory = new FundingStatusHistory();
        fundingStatusHistory.setFunding(funding);
        fundingStatusHistory.setStatus(FundingStatus.ONGOING.getName());
        return  fundingStatusHistory;
    }
}
