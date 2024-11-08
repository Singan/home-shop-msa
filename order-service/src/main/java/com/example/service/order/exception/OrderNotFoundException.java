package com.example.service.order.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}
