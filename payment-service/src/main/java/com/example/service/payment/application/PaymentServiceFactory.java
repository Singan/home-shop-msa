package com.example.service.payment.application;

import com.example.service.payment.api.request.PaymentConfirmRequest;
import com.example.service.payment.api.response.PaymentConfirmResponse;
import com.example.service.payment.application.dto.request.PaymentConfirmRequestDto;
import com.example.service.payment.application.dto.response.PaymentConfirmResponseDto;
import com.example.service.payment.domain.Payment;


public class PaymentServiceFactory {


    public static PaymentConfirmRequestDto createPaymentConfirmRequestDto(PaymentConfirmRequest paymentConfirmRequest, Long userId) {

        return new PaymentConfirmRequestDto(
                userId,
                paymentConfirmRequest.orderId()
        );
    }


    public static PaymentConfirmResponseDto createPaymentConfirmResponseDto(Payment payment) {
        return new PaymentConfirmResponseDto(
                payment.getId(),
                payment.getOrderId(),
                payment.getStatus().getMessage()
        );
    }
}
