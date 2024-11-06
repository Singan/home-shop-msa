package com.example.service.payment.infrastructure.entity;

import com.example.service.payment.domain.Payment;
import com.example.service.payment.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Integer price;
    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    public Payment toPayment() {
        return Payment.builder()
                .id(id)
                .orderId(orderId)
                .createdAt(createdAt)
                .status(status)
                .build();
    }
    public static PaymentEntity fromPayment(Payment payment) {
        return PaymentEntity
                .builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .createdAt(payment.getCreatedAt())
                .status(payment.getStatus())
                .build();
    }

}
