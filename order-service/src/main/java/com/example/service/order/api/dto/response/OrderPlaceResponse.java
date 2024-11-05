package com.example.service.order.api.dto.response;

import java.time.LocalDateTime;

public record OrderPlaceResponse(Long orderId,
                                 Long orderMember,
                                 Long productId,
                                 String productName,
                                 Integer productPrice,
                                 Integer totalPrice,
                                 Integer quantity,
                                 String userName,
                                 String address,
                                 String phone,
                                 String email) {
}
