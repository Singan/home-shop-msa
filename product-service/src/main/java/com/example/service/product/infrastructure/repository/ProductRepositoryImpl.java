package com.example.service.product.infrastructure.repository;

import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import com.example.service.product.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Long productSave(Product product) {
        return jpaProductRepository.save(ProductEntity.fromProduct(product)).getId();
    }

    @Override
    public Slice<Product> findAllProducts(Long cursor, Pageable pageable) {
        return jpaProductRepository.findByIdGreaterThan(cursor, pageable).map(ProductEntity::toProduct);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return jpaProductRepository.findById(id).map(ProductEntity::toProduct);
    }
}
