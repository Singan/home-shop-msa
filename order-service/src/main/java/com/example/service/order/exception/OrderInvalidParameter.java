package com.example.service.order.exception;

public class OrderInvalidParameter extends OrderException {

    public OrderInvalidParameter(){
        super(ErrorCode.INVALID_PARAMETER);
    }
}
