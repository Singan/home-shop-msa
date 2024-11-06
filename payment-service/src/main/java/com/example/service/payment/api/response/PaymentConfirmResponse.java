package com.example.service.payment.api.response;

public record PaymentConfirmResponse(Long paymentId, Long orderId, String paymentStatus) {
}
