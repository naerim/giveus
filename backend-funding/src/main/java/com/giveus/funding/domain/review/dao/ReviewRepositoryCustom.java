package com.giveus.funding.domain.review.dao;

import com.giveus.funding.domain.review.dto.ReviewDetailRes;
import com.giveus.funding.domain.review.dto.ReviewListRes;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ReviewRepositoryCustom {
    List<ReviewListRes> getReviewList(int count);

}
