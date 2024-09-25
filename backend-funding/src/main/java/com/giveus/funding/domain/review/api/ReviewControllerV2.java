package com.giveus.funding.domain.review.api;

import com.giveus.funding.domain.review.application.ReviewService;
import com.giveus.funding.domain.review.dto.ReviewCreateReq;
import com.giveus.funding.domain.review.dto.ReviewDetailRes;
import com.giveus.funding.domain.review.dto.ReviewListRes;
import com.giveus.funding.global.common.response.CommonResponseBody;
import com.giveus.funding.global.common.response.CreateSuccessDto;
import com.giveus.funding.global.config.SwaggerApiSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "[v2] 펀딩 후기 API")
@RestController
@RequestMapping("/api/v2/funding/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewControllerV2 {
    private final ReviewService reviewService;

    @SwaggerApiSuccess(summary = "펀딩 후기 등록", implementation = CreateSuccessDto.class)
    @PostMapping
    public ResponseEntity<CommonResponseBody<CreateSuccessDto>> createReview(
            @Valid @RequestPart ReviewCreateReq reviewCreateReq, @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, reviewService.createReview(reviewCreateReq, file)));
    }

    @SwaggerApiSuccess(summary = "펀딩 후기 전체 조회", implementation = ReviewDetailRes.class)
    @GetMapping
    public ResponseEntity<CommonResponseBody<List<ReviewListRes>>> getReviewList(@RequestParam int size) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, reviewService.getReviewList(size)));
    }

    @SwaggerApiSuccess(summary = "펀딩 후기 상세 조회", implementation = ReviewDetailRes.class)
    @GetMapping("/{fundingNo}")
    public ResponseEntity<CommonResponseBody<ReviewDetailRes>> getReview(@PathVariable int fundingNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, reviewService.getReview(fundingNo)));
    }

}
