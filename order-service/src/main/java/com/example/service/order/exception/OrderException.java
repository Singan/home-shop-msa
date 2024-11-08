package com.example.service.order.exception;

import org.springframework.http.HttpStatus;

public class OrderException extends RuntimeException {
    private final ErrorCode errorCode;

    public OrderException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return errorCode.getStatusCode();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }

    public String getStatusMessage() {
        return errorCode.getMessage();
    }
}
