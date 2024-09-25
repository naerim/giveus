package com.giveus.funding.domain.funding.exception;

public class AlreadyExistFundingException extends RuntimeException {
    public static final String MESSAGE = "이미 추가정보가 존재하는 펀딩입니다.";

    public AlreadyExistFundingException() {
        super(MESSAGE);
    }
}
