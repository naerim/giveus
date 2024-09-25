package com.giveus.admin.domain.usage.exception;

public class UsageHistoryNotFoundException extends RuntimeException {
    public static final String MESSAGE = "펀딩에 사용 내역이 존재하지 않습니다.";

    public UsageHistoryNotFoundException() {
        super(MESSAGE);
    }
}
