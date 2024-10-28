package com.example.service.product.application.usecase;

import com.example.service.product.application.ProductServiceFactory;
import com.example.service.product.application.dto.response.ProductDetailDto;
import com.example.service.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailUseCase {

    private final ProductRepository productRepository;


    public ProductDetailDto productDetail(Long id) {
        return ProductServiceFactory.createProductDetailDto(
                productRepository.findOne(id).orElseThrow()
        );
    }
}
