package com.example.service.payment.domain.enums;

public enum PaymentStatus {

    success("결제 성공"),
    fail("결제 실패");
    private String message;

    PaymentStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
