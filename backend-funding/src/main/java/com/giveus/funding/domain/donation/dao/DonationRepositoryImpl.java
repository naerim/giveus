package com.giveus.funding.domain.donation.dao;

import com.giveus.funding.domain.donation.domain.MemberFunding;
import com.giveus.funding.domain.donation.domain.QMember;
import com.giveus.funding.domain.donation.domain.QMemberFunding;
import com.giveus.funding.domain.donation.dto.DonationAmountRes;
import com.giveus.funding.domain.donation.dto.DonationListRes;
import com.giveus.funding.domain.donation.dto.MemberDonationListRes;
import com.giveus.funding.domain.funding.domain.QFunding;
import com.giveus.funding.domain.funding.domain.QFundingDetail;
import com.giveus.funding.domain.funding.domain.QFundingStatusHistory;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * @author 이하늬
 * @since 1.0
 */
public class DonationRepositoryImpl extends QuerydslRepositorySupport implements DonationRepositoryCustom {

    private static final QFunding qFunding = QFunding.funding;
    private static final QFundingDetail qFundingDetail = QFundingDetail.fundingDetail;
    private static final QFundingStatusHistory qFundingStatusHistory = QFundingStatusHistory.fundingStatusHistory;
    private static final QMember qMember = QMember.member;
    private static final QMemberFunding qMemberFunding = QMemberFunding.memberFunding;

    public DonationRepositoryImpl() {
        super(MemberFunding.class);
    }


    @Override
    public List<MemberDonationListRes> getMemberFundingList(int memberNo) {

        QFundingStatusHistory qFundingStatusHistory2 = QFundingStatusHistory.fundingStatusHistory;

        return from(qFundingDetail)
                .leftJoin(qFundingDetail.funding, qFunding)
                .rightJoin(qMemberFunding).on(qFunding.eq(qMemberFunding.funding))
                .select(Projections.fields(MemberDonationListRes.class,
                        qFundingDetail.thumbnailUrl, qFunding.fundingNo, qFunding.title,
                        ExpressionUtils.as(from(qFundingStatusHistory)
                                .select(qFundingStatusHistory.status)
                                .where(qFundingStatusHistory.fundingStatusHistoryNo
                                        .eq(from(qFundingStatusHistory2)
                                                .select(qFundingStatusHistory2.fundingStatusHistoryNo.max())
                                                .where(qFundingStatusHistory2.funding.eq(qFunding)))), "status"),
                        qFunding.targetAmount, ExpressionUtils.as(from(qMemberFunding)
                                .select(qMemberFunding.amount.sum())
                                .where(qMemberFunding.funding.eq(qFunding)), "totalAmount")
                        , qFunding.startDate, qFunding.endDate, qFunding.birth, qMemberFunding.memberFundingNo, qMemberFunding.amount, qMemberFunding.createdAt))
                .where(qMemberFunding.member.memberNo.eq(memberNo))
                .fetch();

    }

    @Override
    public List<DonationListRes> getParticipantListByFunding(int fundingNo) {
        return from(qMemberFunding)
                .innerJoin(qMemberFunding.member, qMember)
                .on(qMemberFunding.funding.fundingNo.eq(fundingNo))
                .select(Projections.fields(DonationListRes.class,
                        qMemberFunding.memberFundingNo,
                        qMemberFunding.isPublic.when(true).then(qMember.name)
                                .when(false).then(qMember.nickname)
                                .otherwise("").as("name"),
                        qMemberFunding.amount, qMemberFunding.createdAt, qMemberFunding.isPublic,
                        qMemberFunding.isPublic.when(true).then(qMember.imageUrl)
                                .otherwise("").as("imageUrl")))
                .orderBy(qMemberFunding.createdAt.desc())
                .where(qMemberFunding.funding.fundingNo.eq(fundingNo))
                .fetch();
    }

    @Override
    public DonationAmountRes getDonationAmount() {
        return from(qMemberFunding)
                .select(Projections.constructor(DonationAmountRes.class,
                        qMemberFunding.amount.sum()))
                .fetchOne();
    }

    @Override
    public List<DonationListRes> getParticipantList(int limit) {
        return from(qMemberFunding)
                .innerJoin(qMemberFunding.member, qMember)
                .select(Projections.fields(DonationListRes.class,
                        qMemberFunding.memberFundingNo,
                        qMemberFunding.isPublic.when(true).then(qMember.name)
                                .otherwise(qMember.nickname).as("name"),
                        qMemberFunding.amount, qMemberFunding.createdAt, qMemberFunding.isPublic,
                        qMemberFunding.isPublic.when(true).then(qMember.imageUrl)
                                .otherwise("").as("imageUrl")))
                .orderBy(qMemberFunding.createdAt.desc())
                .limit(limit)
                .fetch();

    }

}
