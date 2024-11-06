package com.example.service.payment.application.interfaces;

import com.example.service.payment.api.request.PaymentConfirmRequest;
import com.example.service.payment.api.response.PaymentConfirmResponse;

public interface PaymentService {


    PaymentConfirmResponse paymentConfirm(Long userId, PaymentConfirmRequest paymentConfirmRequest);
}
