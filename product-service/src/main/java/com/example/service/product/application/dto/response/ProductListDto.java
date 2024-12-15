package com.example.service.product.application.dto.response;

import java.util.List;

public record ProductListDto(
        List<ProductListItemDto> products, Long nextCursor
) {
}
