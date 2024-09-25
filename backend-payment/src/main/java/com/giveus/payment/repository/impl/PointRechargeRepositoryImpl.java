package com.giveus.payment.repository.impl;

import com.giveus.payment.dto.response.PointRechargeRes;
import com.giveus.payment.entity.QPointRecharge;
import com.giveus.payment.repository.PointRechargeRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PointRechargeRepositoryImpl implements PointRechargeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    QPointRecharge qPointRecharge = QPointRecharge.pointRecharge;

    @Override
    public List<PointRechargeRes> getRechargeList(int memberNo) {
        return jpaQueryFactory.from(qPointRecharge)
                .select(Projections.fields(PointRechargeRes.class,
                        qPointRecharge.pointNo, qPointRecharge.amount, qPointRecharge.createdAt, qPointRecharge.content, qPointRecharge.paymentType))
                .where(qPointRecharge.memberNo.eq(memberNo))
                .fetch();
    }

}
