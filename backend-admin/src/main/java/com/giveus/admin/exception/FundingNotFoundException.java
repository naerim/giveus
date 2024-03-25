package com.giveus.admin.exception;

public class FundingNotFoundException extends RuntimeException {
    public static final String MESSAGE = "존재하지 않는 펀딩입니다.";

    public FundingNotFoundException() {
        super(MESSAGE);
    }
}
