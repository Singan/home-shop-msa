package com.example.service.product.application.dto.response;

public record ProductListItemDto(
        Long id,
        Integer stock,
        String name,


        Integer price,
        String openDateTime,
        String leftTime
) {

}
