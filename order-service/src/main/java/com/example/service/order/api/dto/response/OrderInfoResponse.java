package com.example.service.order.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public record OrderInfoResponse(
        Long productId,
        Long userId,
        Integer stock,
        Long orderId,
        LocalDateTime orderTime,
        String orderStatus) {
}
