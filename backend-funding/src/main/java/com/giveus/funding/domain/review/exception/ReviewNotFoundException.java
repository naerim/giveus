package com.giveus.funding.domain.review.exception;

public class ReviewNotFoundException extends RuntimeException {
    public static final String MESSAGE = "존재하지 않는 리뷰입니다.";

    public ReviewNotFoundException() {
        super(MESSAGE);
    }
}
