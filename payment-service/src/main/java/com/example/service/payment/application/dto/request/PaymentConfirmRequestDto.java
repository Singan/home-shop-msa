package com.example.service.payment.application.dto.request;

public record PaymentConfirmRequestDto(Long userId, Long orderId, Integer price) {
}
