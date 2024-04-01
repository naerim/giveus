package com.giveus.notification.exception;

public class NoMemberExistException extends RuntimeException {

    private static final String MESSAGE = "해당 Member 는 존재하지 않습니다.";

    public NoMemberExistException() {
        super(MESSAGE);
    }
}
