package com.example.service.payment.api.controller;

import com.example.service.payment.api.request.PaymentConfirmRequest;
import com.example.service.payment.api.response.PaymentConfirmResponse;
import com.example.service.payment.application.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    public PaymentConfirmResponse paymentConfirm(@RequestHeader(name = "X-User-Id") Long userId
            , @RequestBody PaymentConfirmRequest paymentConfirmRequest) {
        return paymentService.paymentConfirm(userId,paymentConfirmRequest);
    }
}
