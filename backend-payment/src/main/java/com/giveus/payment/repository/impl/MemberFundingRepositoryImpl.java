package com.giveus.payment.repository.impl;

import com.giveus.payment.entity.QMemberFunding;
import com.giveus.payment.repository.MemberFundingRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberFundingRepositoryImpl implements MemberFundingRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QMemberFunding qMemberFunding = QMemberFunding.memberFunding;


    @Override
    public Integer getTotalAmount(int fundingNo) {
        return jpaQueryFactory.from(qMemberFunding)
                .select(qMemberFunding.amount.sum())
                .where(qMemberFunding.fundingNo.eq(fundingNo))
                .fetchOne();
    }
}
