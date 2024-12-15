package com.example.service.product.application.usecase;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSaveUseCase {

    private final ProductRepository productRepository;

    public Long productSave(ProductAddRequest product) {
        return productRepository.productSave(
                Product.builder()
                        .name(product.name())
                        .price(product.price())
                        .stock(product.stock())
                        .description(product.description())
                        .openDateTime(product.getOpenTime())
                        .build()
        );
    }

}
