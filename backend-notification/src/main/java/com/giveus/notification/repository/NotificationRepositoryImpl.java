package com.giveus.notification.repository;

import com.giveus.notification.dto.response.FundingReviewListRes;
import com.giveus.notification.dto.response.UsageHistoryListRes;
import com.giveus.notification.entity.*;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class NotificationRepositoryImpl extends QuerydslRepositorySupport implements NotificationRepositoryCustom{

    private static final QFunding qFunding = QFunding.funding;
    private static final QFundingDetail qFundingDeatil = QFundingDetail.fundingDetail;
    private static final QMemberFunding qMemberFunding = QMemberFunding.memberFunding;
    private static final QMemberSetting qMemberSetting = QMemberSetting.memberSetting;
    private static final QMemberDevice qMemberDevice = QMemberDevice.memberDevice;
    private static final QMember qMember = QMember.member;

    public NotificationRepositoryImpl() {super(Notification.class);}

    @Override
    public List<FundingReviewListRes> getFundingReviewList(int fundingNo) {
        return from(qMemberFunding)
                .rightJoin(qMemberDevice).on(qMemberFunding.member.eq(qMemberDevice.member))
                .where(qMemberFunding.funding.fundingNo.eq(fundingNo))
                .select(Projections.fields(FundingReviewListRes.class,
                        qMemberFunding.member.memberNo, qMemberFunding.funding.fundingNo, qMemberDevice.deviceToken,
                        ExpressionUtils.as(from(qFunding)
                                .where(qFunding.funding.eq(qMemberFunding.funding))
                                .select(qFunding.title), "title"),
                        ExpressionUtils.as(from(qFundingDeatil)
                                .where(qFundingDeatil.funding.eq(qMemberFunding.funding))
                                .select(qFundingDeatil.thumbnailUrl), "thumbnailUrl"),
                        ExpressionUtils.as(from(qMemberSetting)
                                .where(qMemberSetting.member.eq(qMemberFunding.member))
                                .select(qMemberSetting.fundingReview), "fundingReview"))).distinct()
                .fetch();
    }

    public List<UsageHistoryListRes> getUsageHistoryList(int fundingNo) {
        return from(qMemberFunding)
                .rightJoin(qMemberDevice).on(qMemberFunding.member.eq(qMemberDevice.member))
                .where(qMemberFunding.funding.fundingNo.eq(fundingNo))
                .select(Projections.fields(UsageHistoryListRes.class,
                        qMemberFunding.member.memberNo, qMemberFunding.funding.fundingNo, qMemberDevice.deviceToken,
                        ExpressionUtils.as(from(qFunding)
                                .where(qFunding.funding.eq(qMemberFunding.funding))
                                .select(qFunding.title), "title"),
                        ExpressionUtils.as(from(qMemberSetting)
                                .where(qMemberSetting.member.eq(qMemberFunding.member))
                                .select(qMemberSetting.fundingReview), "usageHistory"))).distinct()
                .fetch();
    }
}
