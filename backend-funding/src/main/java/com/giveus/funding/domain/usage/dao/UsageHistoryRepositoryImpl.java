package com.giveus.funding.domain.usage.dao;

import com.giveus.funding.domain.usage.domain.QUsageHistory;
import com.giveus.funding.domain.usage.domain.UsageHistory;
import com.giveus.funding.domain.usage.dto.FundingUsageListRes;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class UsageHistoryRepositoryImpl extends QuerydslRepositorySupport implements UsageHistoryRepositoryCustom {

    private static final QUsageHistory qUsageHistory = QUsageHistory.usageHistory;

    public UsageHistoryRepositoryImpl() {
        super(UsageHistory.class);
    }


    @Override
    public List<FundingUsageListRes> getUsageList(int fundingNo) {
        return from(qUsageHistory)
                .select(Projections.fields(FundingUsageListRes.class,
                        qUsageHistory.usageHistoryNo, qUsageHistory.category,
                        qUsageHistory.content, qUsageHistory.count,
                        qUsageHistory.amount))
                .orderBy(qUsageHistory.usageHistoryNo.desc())
                .where(qUsageHistory.funding.fundingNo.eq(fundingNo))
                .fetch();
    }
}
