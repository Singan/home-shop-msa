package com.example.service.payment.application.dto.message;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StockDecreaseDto {
    private final Long productId;
    private final Integer quantity;
}
