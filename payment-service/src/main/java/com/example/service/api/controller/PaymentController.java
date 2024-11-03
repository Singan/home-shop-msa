package com.example.service.api.controller;

import com.example.service.api.request.PaymentInitiationRequest;
import com.example.service.application.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public void paymentInitiate(@RequestHeader(name = "X-User-Id") Long userId
            , @RequestBody PaymentInitiationRequest paymentInitiationRequest) {
        paymentService.setupPayment(paymentInitiationRequest);
    }
}