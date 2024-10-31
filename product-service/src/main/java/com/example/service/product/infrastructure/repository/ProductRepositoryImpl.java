package com.example.service.product.infrastructure.repository;

import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import com.example.service.product.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductRedisRepository productRedisRepository;

    @Override
    public Long productSave(Product product) {
        return productJpaRepository.save(ProductEntity.fromProduct(product)).getId();
    }

    @Override
    public Slice<Product> findAllProducts(Long cursor, Pageable pageable) {
        return productJpaRepository.findByIdGreaterThan(cursor, pageable).map(ProductEntity::toProduct);
    }

    @Override
    @Cacheable(value = "products", key = "'product:' + #id")

    public Optional<Product> findOne(Long id) {
        return productJpaRepository.findById(id).map(ProductEntity::toProduct);
    }
    @Override
    public void findByWarmingProductList(LocalDateTime localDateTime) {
        List<Product> productList=
                productJpaRepository.findByWarmingProductList(localDateTime).stream().map(ProductEntity::toProduct).toList();
        productRedisRepository.productStockWarmingUp(productList);
    }
}
