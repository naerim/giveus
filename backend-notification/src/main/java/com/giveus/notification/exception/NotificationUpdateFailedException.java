package com.giveus.notification.exception;

public class NotificationUpdateFailedException extends RuntimeException {
    public static final String MESSAGE = "알림 읽음 여부 수정에 실패하셨습니다.";

    public NotificationUpdateFailedException() {
        super(MESSAGE);
    }
}
