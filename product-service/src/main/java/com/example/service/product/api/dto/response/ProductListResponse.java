package com.example.service.product.api.dto.response;

import java.util.List;

public record ProductListResponse(Long nextCursor , List<ProductListItemResponse> items) {
}
