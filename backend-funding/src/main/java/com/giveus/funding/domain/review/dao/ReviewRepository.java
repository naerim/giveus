package com.giveus.funding.domain.review.dao;

import com.giveus.funding.domain.review.domain.Review;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

//    @Query(value = "SELECT new com.giveus.funding.domain.review.dto.ReviewDetailRes(r.reviewNo, r.funding.fundingNo, r.title, r.content, r.createdAt, r.url) " +
//                    "FROM Review r " +
//                    "ORDER BY r.createdAt limit :count", nativeQuery = true)
    @Query(value = "SELECT r.reviewNo, r.funding.fundingNo, r.title, r.content, r.createdAt, r.url " +
                    "FROM Review r " +
                    "ORDER BY r.createdAt limit :count", nativeQuery = true)
    List<Review> findReviews(@Param("count") int count);

    Optional<Review> findByFunding_FundingNo(int fundingNo);

    Optional<Review> findReviewByFunding_FundingNo(int fundingNo);
}
