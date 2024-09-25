package com.giveus.gateway.exception;

public class JwtException extends RuntimeException{
    public JwtException(String msg) {
        super(msg);
    }
}
