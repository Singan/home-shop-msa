package com.example.service.domain.enums;

public enum OrderStatus {
    PENDING("주문이 접수 전"),
    SHIPPED("주문이 발송됨"),
    DELIVERED("주문이 고객에게 전달됨"),
    CANCELLED("주문이 취소됨"),
    RETURNED("주문이 반품됨");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


