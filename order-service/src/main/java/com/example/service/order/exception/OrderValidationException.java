package com.example.service.order.exception;

public class OrderValidationException extends OrderException {
    public OrderValidationException() {
        super(ErrorCode.UNAUTHORIZED_ORDER_ACCESS);
    }
}
