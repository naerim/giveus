package com.giveus.admin.global.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ErrorResponseDto {
    private int statusCode;
    private String message;

    public ErrorResponseDto(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.message = message;
    }
}