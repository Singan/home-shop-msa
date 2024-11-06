package com.example.service.payment.infrastructure.repository;

import com.example.service.payment.domain.Payment;
import com.example.service.payment.domain.repository.PaymentRepository;
import com.example.service.payment.infrastructure.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final JpaPaymentRepository jpaPaymentRepository;


    public void save(Payment payment) {
        jpaPaymentRepository.save(PaymentEntity.fromPayment(payment));
    }
}
