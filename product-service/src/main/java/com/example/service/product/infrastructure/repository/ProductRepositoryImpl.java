package com.example.service.product.infrastructure.repository;

import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import com.example.service.product.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Long productSave(Product product) {
        return jpaProductRepository.save(ProductEntity.fromProduct(product)).getId();
    }
}
