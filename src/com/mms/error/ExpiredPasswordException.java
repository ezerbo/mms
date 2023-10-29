package com.mms.error;

public class ExpiredPasswordException extends RuntimeException {

    public ExpiredPasswordException(String message, Object... args) {
        super(String.format(message, args));
    }
}
