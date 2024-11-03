package com.example.service.application;

import com.example.service.api.request.PaymentInitiationRequest;
import com.example.service.application.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    @Override
    public void setupPayment(PaymentInitiationRequest paymentInitiationRequest) {

    }
}
