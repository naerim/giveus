package com.giveus.funding.domain.funding.dao;

import com.giveus.funding.domain.donation.domain.QMemberFunding;
import com.giveus.funding.domain.funding.domain.Funding;
import com.giveus.funding.domain.funding.domain.QFunding;
import com.giveus.funding.domain.funding.domain.QFundingDetail;
import com.giveus.funding.domain.funding.domain.QFundingStatusHistory;
import com.giveus.funding.domain.funding.dto.FundingDetailRes;
import com.giveus.funding.domain.funding.dto.FundingListRes;
import com.giveus.funding.domain.review.domain.QReview;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author 이하늬
 * @since 1.0
 */
public class FundingRepositoryImpl extends QuerydslRepositorySupport implements FundingRepositoryCustom {

    private static final QFunding qFunding = QFunding.funding;
    private static final QFundingDetail qFundingDetail = QFundingDetail.fundingDetail;
    private static final QFundingStatusHistory qFundingStatusHistory = QFundingStatusHistory.fundingStatusHistory;
    private static final QReview qReview = QReview.review;
    private static final QMemberFunding qMemberFunding = QMemberFunding.memberFunding;

    public FundingRepositoryImpl() {
        super(Funding.class);
    }

    @Override
    public List<FundingListRes> getFundingList() {
        return getFundingListResJPQLQuery()
                .fetch();


    }

    @Override
    public Optional<FundingDetailRes> getFunding(int fundingNo) {
        QFundingStatusHistory qFundingStatusHistory2 = QFundingStatusHistory.fundingStatusHistory;
        return Optional.ofNullable(from(qFundingDetail)
                .leftJoin(qFundingDetail.funding, qFunding)
                .leftJoin(qReview).on(qFunding.eq(qReview.funding))
                .select(Projections.fields(FundingDetailRes.class,
                        qFundingDetail.thumbnailUrl, qFunding.fundingNo, qFunding.title,
                        ExpressionUtils.as(from(qFundingStatusHistory)
                                .select(qFundingStatusHistory.status)
                                .where(qFundingStatusHistory.fundingStatusHistoryNo
                                        .eq(from(qFundingStatusHistory2)
                                                .select(qFundingStatusHistory2.fundingStatusHistoryNo.max())
                                                .where(qFundingStatusHistory2.funding.fundingNo.eq(fundingNo))
                                        )
                                ), "status"
                        )
                        , qFunding.targetAmount, qFunding.startDate, qFunding.endDate,
                        ExpressionUtils.as(from(qMemberFunding)
                                .select(qMemberFunding.amount.sum())
                                .where(qMemberFunding.funding.fundingNo.eq(fundingNo)), "totalAmount")
                        , qFunding.createdAt, qFunding.birth, qFundingDetail.content,
                        ExpressionUtils.as(ExpressionUtils.isNotNull(qReview.reviewNo), "isRegReview"))
                )
                .where(qFundingDetail.funding.fundingNo.eq(fundingNo)).fetchOne());
    }

    @Override
    public List<FundingListRes> getFundingByFundingTitle(String query) {
        return getFundingListResJPQLQuery()
                .where(qFunding.title.contains(query))
                .fetch();
    }

    @Override
    public List<FundingListRes> getFundingListSortByEndDate(Integer size) {

        if (size <= 0) {
            return getFundingListResJPQLQuery()
                    .leftJoin(qFundingStatusHistory).on(qFunding.eq(qFundingStatusHistory.funding))
                    .where(qFunding.endDate.goe(LocalDate.now())) // 종료일이 오늘이거나 오늘 이전인 것 중에
                    .where(qFundingStatusHistory.status.ne("진행완료"))
                    .orderBy(qFunding.endDate.asc())
                    .fetch();
        }
        return getFundingListResJPQLQuery()
                .leftJoin(qFundingStatusHistory).on(qFunding.eq(qFundingStatusHistory.funding))
                .where(qFunding.endDate.goe(LocalDate.now())) // 종료일이 오늘이거나 오늘 이전인 것 중에
                .where(qFundingStatusHistory.status.ne("진행완료"))
                .orderBy(qFunding.endDate.asc())
                .limit(size)
                .fetch();
    }


    /**
     * 펀딩 목록 JPQLQuery를 조회하는 메서드입니다.
     *
     * @return 조회한 펀딩 목록
     */
    private JPQLQuery<FundingListRes> getFundingListResJPQLQuery() {
        QFundingStatusHistory qFundingStatusHistory2 = QFundingStatusHistory.fundingStatusHistory;

        return from(qFundingDetail)
                .leftJoin(qFundingDetail.funding, qFunding)
                .select(Projections.fields(FundingListRes.class,
                        qFundingDetail.thumbnailUrl, qFunding.fundingNo, qFunding.title,
                        ExpressionUtils.as(from(qFundingStatusHistory)
                                .select(qFundingStatusHistory.status)
                                .where(qFundingStatusHistory.fundingStatusHistoryNo
                                        .eq(from(qFundingStatusHistory2)
                                                .select(qFundingStatusHistory2.fundingStatusHistoryNo.max())
                                                .where(qFundingStatusHistory2.funding.eq(qFunding)))), "status"),
                        qFunding.targetAmount, ExpressionUtils.as(from(qMemberFunding)
                                .select(qMemberFunding.amount.sum())
                                .where(qMemberFunding.funding.eq(qFunding)), "totalAmount"),
                        ExpressionUtils.as(from(qMemberFunding)
                                .select(qMemberFunding.memberFundingNo.count())
                                .where(qMemberFunding.funding.eq(qFunding)), "donationCnt")
                        , qFunding.startDate, qFunding.endDate, qFunding.createdAt, qFunding.birth))
                .where(qFunding.endDate.goe(LocalDate.now()))
                ;
    }
}
