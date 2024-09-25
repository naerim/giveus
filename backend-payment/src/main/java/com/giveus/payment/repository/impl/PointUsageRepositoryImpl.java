package com.giveus.payment.repository.impl;

import com.giveus.payment.dto.response.PointUsageRes;
import com.giveus.payment.entity.QFunding;
import com.giveus.payment.entity.QMemberFunding;
import com.giveus.payment.entity.QPointUsage;
import com.giveus.payment.repository.PointUsageRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PointUsageRepositoryImpl implements PointUsageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QPointUsage qPointUsage = QPointUsage.pointUsage;
    private QMemberFunding qMemberFunding = QMemberFunding.memberFunding;
    private QFunding qFunding = QFunding.funding;

    @Override
    public List<PointUsageRes> getUsageList(int memberNo) {
        return jpaQueryFactory.from(qPointUsage)
                .leftJoin(qMemberFunding).on(qPointUsage.pointNo.eq(qMemberFunding.pointNo))
                .leftJoin(qFunding).on(qMemberFunding.fundingNo.eq(qFunding.fundingNo))
                .select(Projections.fields(PointUsageRes.class,
                        qPointUsage.pointNo, qPointUsage.amount, qPointUsage.createdAt, qFunding.title))
                .where(qPointUsage.memberNo.eq(memberNo))
                .fetch();

    }

}
