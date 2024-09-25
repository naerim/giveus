package com.giveus.payment.repository.impl;

import com.giveus.payment.entity.QFundingStatusHistory;
import com.giveus.payment.repository.FundingStatusHistoryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingStatusHistoryRepositoryImpl implements FundingStatusHistoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QFundingStatusHistory qFundingStatusHistory = QFundingStatusHistory.fundingStatusHistory;

    @Override
    public void updateFundingStatusToFinish(int fundingNo) {
        jpaQueryFactory.update(qFundingStatusHistory)
                .set(qFundingStatusHistory.status, "진행완료")
                .where(qFundingStatusHistory.funding.fundingNo.eq(fundingNo))
                .execute();
    }
}
