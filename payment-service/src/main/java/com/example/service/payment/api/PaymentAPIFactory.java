package com.example.service.payment.api;

import com.example.service.payment.api.request.PaymentConfirmRequest;
import com.example.service.payment.api.response.PaymentConfirmResponse;
import com.example.service.payment.application.dto.request.PaymentConfirmRequestDto;
import com.example.service.payment.application.dto.response.PaymentConfirmResponseDto;

public class PaymentAPIFactory {

    public static PaymentConfirmResponse createPaymentConfirmResponse(PaymentConfirmResponseDto paymentConfirmResponseDto) {

        return new PaymentConfirmResponse(
                paymentConfirmResponseDto.paymentId(),
                paymentConfirmResponseDto.orderId(),
                paymentConfirmResponseDto.paymentStatus()
        );

    }
}
