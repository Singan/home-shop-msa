package com.example.service.order.infrastructure.repository;

import com.example.service.order.infrastructure.product.ProductClient;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductClient productClient;
    @Async
    public CompletableFuture<ProductDetailDto> getProductDetail(Long id) {
        return CompletableFuture.completedFuture(productClient.getProductDetail(id));
    }

}
