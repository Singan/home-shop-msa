package com.example.service.product.application.dto.response;

import java.time.LocalDateTime;

public record ProductDetailDto(
        Long id,

        Integer stock,

        String name,


        Integer price,

        String openDateTime,
        String leftTime,
        String description
) {
}
