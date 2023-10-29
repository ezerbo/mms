package com.mms.error;

public class InvalidCrdentialsException extends RuntimeException {

    public InvalidCrdentialsException(String message, Object... args) {
        super(String.format(message, args));
    }
}
