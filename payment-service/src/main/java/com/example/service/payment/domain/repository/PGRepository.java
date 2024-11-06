package com.example.service.payment.domain.repository;

import com.example.service.payment.domain.Payment;

public interface PGRepository {
    boolean paymentGatewayToSend(Payment payment);
}
