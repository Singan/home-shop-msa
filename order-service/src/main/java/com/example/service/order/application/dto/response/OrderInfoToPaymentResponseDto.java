package com.example.service.order.application.dto.response;

import com.example.service.order.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderInfoToPaymentResponseDto(Long orderId,
                                            Long productId,
                                            Long userId,
                                            Integer quantity,
                                            LocalDateTime orderTime,
                                            OrderStatus orderStatus) {
}
