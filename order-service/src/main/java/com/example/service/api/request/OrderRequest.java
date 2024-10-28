package com.example.service.api.request;

public record OrderRequest(
        Long productId, Long buyStock
) {
}
