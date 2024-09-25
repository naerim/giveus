package com.giveus.funding.domain.review.exception;

public class AlreadyExistReviewException extends RuntimeException {
    public static final String MESSAGE = "이미 후기가 존재하는 펀딩입니다.";

    public AlreadyExistReviewException() {
        super(MESSAGE);
    }
}
