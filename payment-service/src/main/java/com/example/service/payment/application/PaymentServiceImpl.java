package com.example.service.payment.application;

import com.example.service.payment.api.request.PaymentInitiationRequest;
import com.example.service.payment.application.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    @Override
    public void setupPayment(PaymentInitiationRequest paymentInitiationRequest) {

    }
}
