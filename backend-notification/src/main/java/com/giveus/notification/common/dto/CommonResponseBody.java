package com.giveus.notification.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class CommonResponseBody<T> {
    private Integer code;
    private T data;

    public CommonResponseBody(HttpStatus status, T data) {
        this.code = status.value();
        this.data = data;
    }
}