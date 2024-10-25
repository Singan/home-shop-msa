package com.example.service.product.application.dto.response;

import com.example.service.product.api.dto.response.ProductListItemResponse;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductListItemDto(
        Long id,
        Integer stock,
        String name,


        Integer price,
        LocalDateTime openDateTime
) {
    public ProductListItemResponse getProductListItem(){
        return new ProductListItemResponse(id, stock, name, price, openDateTime);
    }
}
