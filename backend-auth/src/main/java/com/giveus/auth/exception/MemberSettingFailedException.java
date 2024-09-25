package com.giveus.auth.exception;

public class MemberSettingFailedException extends RuntimeException {

    private static final String MESSAGE = "[ERROR] Member 알림 설정 관련 처리에 실패하였습니다.";

    public MemberSettingFailedException() {
        super(MESSAGE);
    }
}
