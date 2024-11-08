package com.example.service.order.exception;

public class OrderStockOutOfException extends OrderException {
    public OrderStockOutOfException() {
        super(ErrorCode.OUT_OF_STOCK);
    }
}
