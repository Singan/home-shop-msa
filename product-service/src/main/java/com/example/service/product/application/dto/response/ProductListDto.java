package com.example.service.product.application.dto.response;

import com.example.service.product.domain.Product;

import java.util.List;

public record ProductListDto(
        List<ProductListItemDto> products, Long nextCursor
) {
}
