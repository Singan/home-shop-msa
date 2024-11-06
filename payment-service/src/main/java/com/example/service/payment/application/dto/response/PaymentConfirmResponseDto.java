package com.example.service.payment.application.dto.response;

public record PaymentConfirmResponseDto(Long paymentId,Long orderId, String paymentStatus) {
}
