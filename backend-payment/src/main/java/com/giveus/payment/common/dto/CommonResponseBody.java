package com.giveus.payment.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponseBody<T> {
    private Integer code;
    private T data;

    public CommonResponseBody(HttpStatus status, T data) {
        this.code = status.value();
        this.data = data;
    }
}
