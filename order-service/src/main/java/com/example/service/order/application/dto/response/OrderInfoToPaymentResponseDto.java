package com.example.service.order.application.dto.response;

import com.example.service.order.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderInfoToPaymentResponseDto(Long orderId,
                                            Long productId,
                                            Long userId,
                                            LocalDateTime orderTime,
                                            OrderStatus orderStatus) {
}
