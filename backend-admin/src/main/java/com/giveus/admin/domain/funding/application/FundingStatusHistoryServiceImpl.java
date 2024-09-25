package com.giveus.admin.domain.funding.application;

import com.giveus.admin.domain.funding.domain.Funding;
import com.giveus.admin.domain.funding.domain.FundingStatusHistory;
import com.giveus.admin.domain.funding.dao.FundingStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundingStatusHistoryServiceImpl implements FundingStatusHistoryService {
    private final FundingStatusHistoryRepository fundingStatusHistoryRepository;

    @Override
    public FundingStatusHistory createHistory(Funding funding) {
        FundingStatusHistory fundingStatusHistory = FundingStatusHistoryTransfer.dtoToEntityCreated(funding);
        return fundingStatusHistoryRepository.save(fundingStatusHistory);
    }
}
