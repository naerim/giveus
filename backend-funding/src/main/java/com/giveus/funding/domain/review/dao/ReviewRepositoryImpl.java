package com.giveus.funding.domain.review.dao;

import com.giveus.funding.domain.donation.domain.QMemberFunding;
import com.giveus.funding.domain.funding.domain.QFunding;
import com.giveus.funding.domain.funding.domain.QFundingDetail;
import com.giveus.funding.domain.review.domain.QReview;
import com.giveus.funding.domain.review.domain.Review;
import com.giveus.funding.domain.review.dto.ReviewListRes;

import com.giveus.funding.domain.usage.domain.QUsageHistory;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {

    private static final QReview qReview = QReview.review;
    private static final QFunding qFunding = QFunding.funding;
    private static final QMemberFunding qMemberFunding = QMemberFunding.memberFunding;
    private static final QUsageHistory qUsageHistory = QUsageHistory.usageHistory;
    private static final QFundingDetail qFundingDetail = QFundingDetail.fundingDetail;

    public ReviewRepositoryImpl() {super((Review.class));}


    @Override
    public List<ReviewListRes> getReviewList(int size) {
        if (size <= 0) {
            return getReviewListResJPQLQuery()
                    .orderBy(qReview.createdAt.desc())
                    .fetch();
        }
        return getReviewListResJPQLQuery()
                .orderBy(qReview.createdAt.desc())
                .limit(size)
                .fetch();
    }

    private JPQLQuery<ReviewListRes> getReviewListResJPQLQuery() {
        return from(qReview)
                .leftJoin(qReview.funding, qFunding)
                .select(Projections.fields(ReviewListRes.class,
                        qFunding.fundingNo, qFunding.title, qFunding.targetAmount,
                        ExpressionUtils.as(from(qUsageHistory)
                                .where(qUsageHistory.funding.eq(qFunding))
                                .select(new CaseBuilder()
                                        .when(qUsageHistory.usageHistoryNo.max().isNotNull())
                                        .then(true)
                                        .otherwise(false)), "existUsageHistory"),
                        ExpressionUtils.as(from(qMemberFunding)
                                .where(qMemberFunding.funding.eq(qFunding))
                                .select(qMemberFunding.memberFundingNo.count()), "memberFundingCount"),
                        ExpressionUtils.as(from(qFundingDetail)
                                .where(qFundingDetail.funding.eq(qFunding))
                                .select(qFundingDetail.thumbnailUrl), "imageUrl"),
                        ExpressionUtils.as(from(qReview)
                                .where(qReview.funding.eq(qFunding))
                                .select(qReview.reviewNo), "reviewNo")));
    }
}
