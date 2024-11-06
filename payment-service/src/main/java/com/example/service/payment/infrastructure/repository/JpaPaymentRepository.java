package com.example.service.payment.infrastructure.repository;

import com.example.service.payment.domain.Payment;
import com.example.service.payment.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
