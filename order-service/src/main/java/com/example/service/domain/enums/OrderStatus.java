package com.example.service.domain.enums;

public enum OrderStatus {
    PENDING("주문 접수 전"),
    SHIPPED("배송 중"),
    DELIVERED("배송 완료"),
    CANCELLED("주문 취소"),
    RETURNED("주문 반품");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


