package com.example.service.payment.infrastructure.repository;

import com.example.service.payment.domain.Payment;
import com.example.service.payment.domain.repository.PaymentRepository;
import com.example.service.payment.infrastructure.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(PaymentEntity.fromPayment(payment)).toPayment();
    }
}
