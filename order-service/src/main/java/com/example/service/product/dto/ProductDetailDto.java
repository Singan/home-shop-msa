package com.example.service.product.dto;

public record ProductDetailDto(Long id,
                               Integer stock,
                               String name,
                               Integer price,
                               String openDateTime,
                               String leftTime,
                               String description) {
}
