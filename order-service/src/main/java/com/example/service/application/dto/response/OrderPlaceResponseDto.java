package com.example.service.application.dto.response;

import java.time.LocalDateTime;

public record OrderPlaceResponseDto(
        Long orderId,
        Long orderMember,
        Long productId,
        String productName,
        Integer productPrice,
        Integer totalPrice,
        Integer quantity,
        LocalDateTime orderDate
) {
}
