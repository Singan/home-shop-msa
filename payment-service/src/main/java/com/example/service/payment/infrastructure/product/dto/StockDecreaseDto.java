package com.example.service.payment.infrastructure.product.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StockDecreaseDto {
    private final Long productId;
    private final Integer quantity;
}
