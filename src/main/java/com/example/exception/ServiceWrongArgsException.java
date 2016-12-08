package com.example.exception;

public class ServiceWrongArgsException extends RuntimeException {
    public ServiceWrongArgsException() {
    }

    public ServiceWrongArgsException(String message) {
        super(message);
    }

    public ServiceWrongArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceWrongArgsException(Throwable cause) {
        super(cause);
    }
}
