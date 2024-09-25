package com.giveus.notification.exception;

public class NotificationNotFoundException extends RuntimeException {
    public static final String MESSAGE = "존재하지 않는 알림입니다.";

    public NotificationNotFoundException() {
        super(MESSAGE);
    }
}
