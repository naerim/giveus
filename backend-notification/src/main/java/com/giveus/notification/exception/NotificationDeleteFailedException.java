package com.giveus.notification.exception;

public class NotificationDeleteFailedException extends RuntimeException {
    public static final String MESSAGE = "알림 삭제에 실패하셨습니다.";

    public NotificationDeleteFailedException() {
        super(MESSAGE);
    }
}
