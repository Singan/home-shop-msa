package com.example.service.order.exception;

public class OrderUnAuthorizedException  extends OrderException {
    public OrderUnAuthorizedException() {
        super(ErrorCode.UNAUTHORIZED_ORDER_ACCESS);
    }
}
