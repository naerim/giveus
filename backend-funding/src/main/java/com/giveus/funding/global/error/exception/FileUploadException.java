package com.giveus.funding.global.error.exception;

public class FileUploadException extends RuntimeException {
    public static final String MESSAGE = "파일 업로드에 실패하였습니다.";

    public FileUploadException() {
        super(MESSAGE);
    }
}
