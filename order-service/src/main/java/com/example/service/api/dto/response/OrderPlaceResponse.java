package com.example.service.api.dto.response;

import java.time.LocalDateTime;

public record OrderPlaceResponse(Long orderId,
                                 Long orderMember,
                                 Long productId,
                                 String productName,
                                 Integer productPrice,
                                 Integer totalPrice,
                                 Integer quantity,
                                 LocalDateTime orderDate) {
}
