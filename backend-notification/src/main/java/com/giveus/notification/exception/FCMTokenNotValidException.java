package com.giveus.notification.exception;

public class FCMTokenNotValidException extends RuntimeException {
    public static final String MESSAGE = "유효하지 않은 FCM Token입니다";

    public FCMTokenNotValidException() {
        super(MESSAGE);
    }
}
