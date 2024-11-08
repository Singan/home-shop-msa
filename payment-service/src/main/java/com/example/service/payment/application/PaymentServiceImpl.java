package com.example.service.payment.application;

import com.example.service.payment.api.PaymentAPIFactory;
import com.example.service.payment.api.request.PaymentConfirmRequest;
import com.example.service.payment.api.response.PaymentConfirmResponse;
import com.example.service.payment.application.interfaces.PaymentService;
import com.example.service.payment.application.usecase.PaymentConfirmUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentConfirmUseCase paymentConfirmUseCase;

    @Override
    public PaymentConfirmResponse paymentConfirm(Long userId, PaymentConfirmRequest paymentConfirmRequest) {

        return PaymentAPIFactory.createPaymentConfirmResponse(paymentConfirmUseCase.
                paymentConfirm(
                        PaymentServiceFactory.createPaymentConfirmRequestDto(paymentConfirmRequest, userId)
                )
        );
    }
}
