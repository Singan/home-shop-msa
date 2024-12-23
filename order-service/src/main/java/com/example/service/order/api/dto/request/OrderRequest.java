package com.example.service.order.api.dto.request;

import jakarta.validation.constraints.Min;

public record OrderRequest(
        Long productId, @Min(1) Integer buyStock
) {
}
