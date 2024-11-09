package com.example.service.order.infrastructure.repository;

import com.example.service.order.infrastructure.product.ProductClient;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductClient productClient;

    public ProductDetailDto getProductDetail(Long id) {
        return productClient.getProductDetail(id);
    }

}
