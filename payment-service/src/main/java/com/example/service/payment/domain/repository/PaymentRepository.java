package com.example.service.payment.domain.repository;

import com.example.service.payment.domain.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
