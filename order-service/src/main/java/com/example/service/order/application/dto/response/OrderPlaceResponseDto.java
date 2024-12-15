package com.example.service.order.application.dto.response;

public record OrderPlaceResponseDto(
        Long orderId,
        Long orderMember,
        Long productId,
        String productName,
        Integer productPrice,
        Integer totalPrice,
        Integer quantity,
        String userName,
        String address,
        String phone,
        String email
) {
}
