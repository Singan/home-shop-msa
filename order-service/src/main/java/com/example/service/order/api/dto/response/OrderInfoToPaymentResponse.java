package com.example.service.order.api.dto.response;

import com.example.service.order.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderInfoToPaymentResponse(
        Long productId,
        Long orderId,
        Integer quantity,
        LocalDateTime orderTime,
        OrderStatus orderStatus,
        Integer orderPrice
) {
}
