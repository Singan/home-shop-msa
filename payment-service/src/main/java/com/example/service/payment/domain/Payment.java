package com.example.service.payment.domain;

import com.example.service.payment.domain.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {
    private Long id;
    @NotNull
    private Long orderId;

    private Integer price;

    private LocalDateTime createdAt;
    @NotNull
    private PaymentStatus status;
}
