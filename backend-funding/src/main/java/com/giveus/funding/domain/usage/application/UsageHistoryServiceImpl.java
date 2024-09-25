package com.giveus.funding.domain.usage.application;

import com.giveus.funding.domain.usage.dto.FundingUsageListRes;
import com.giveus.funding.domain.usage.dao.UsageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageHistoryServiceImpl implements UsageHistoryService {
    private final UsageHistoryRepository usageHistoryRepository;
    @Override
    public List<FundingUsageListRes> getUsageList(int fundingNo) {
        return usageHistoryRepository.getUsageList(fundingNo);
    }
}
