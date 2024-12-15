package com.example.service.order.exception;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}
