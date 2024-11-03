package com.example.service.application.interfaces;

import com.example.service.api.request.PaymentInitiationRequest;

public interface PaymentService {


    void setupPayment(PaymentInitiationRequest paymentInitiationRequest);
}
