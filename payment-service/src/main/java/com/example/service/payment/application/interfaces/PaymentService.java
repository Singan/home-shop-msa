package com.example.service.payment.application.interfaces;

import com.example.service.payment.api.request.PaymentInitiationRequest;

public interface PaymentService {


    void setupPayment(PaymentInitiationRequest paymentInitiationRequest);
}
