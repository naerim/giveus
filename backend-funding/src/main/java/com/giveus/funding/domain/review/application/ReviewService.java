package com.giveus.funding.domain.review.application;

import com.giveus.funding.domain.review.domain.Review;
import com.giveus.funding.domain.review.dto.ReviewCreateReq;
import com.giveus.funding.domain.review.dto.ReviewDetailRes;
import com.giveus.funding.domain.review.dto.ReviewListRes;
import com.giveus.funding.global.common.response.CreateSuccessDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewListRes> getReviewList(int count);

    ReviewDetailRes getReview(int fundingNo);



    /**
     * 펀딩 고유 번호로 후기 엔터티를 조회하는 메서드입니다. 
     * 
     * @param fundingNo 조회할 펀딩 고유 번호
     * @return 조회한 후기 엔터티
     */
    Optional<Review> findReviewEntity(int fundingNo);


    /**
     * 펀딩 후기를 등록하는 메서드입니다.
     *
     * @param reviewCreateReq 등록할 후기 정보
     * @param file 후기 사진
     * @return 등록한 후기의 고유 번호
     */
    CreateSuccessDto createReview(ReviewCreateReq reviewCreateReq, MultipartFile file);
}
