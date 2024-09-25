package com.giveus.admin.domain.member.exception;

public class NoAdminExistException extends RuntimeException {

    private static final String MESSAGE = "[ERROR] 해당 Admin 은 존재하지 않습니다.";

    public NoAdminExistException() {
        super(MESSAGE);
    }
}
