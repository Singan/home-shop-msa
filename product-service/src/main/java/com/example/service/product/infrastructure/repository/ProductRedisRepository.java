package com.example.service.product.infrastructure.repository;

import com.example.service.product.application.ProductServiceFactory;
import com.example.service.product.application.dto.cache.ProductDetailCacheDto;
import com.example.service.product.domain.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRedisRepository {


    private final RedisTemplate<String, ProductDetailCacheDto> productRedisTemplate;
    private final static String PRODUCT_KEY_PREFIX = "product:";

    public Optional<Product> save(Product product) {
        String productKey = PRODUCT_KEY_PREFIX + product.getId();

        productRedisTemplate.opsForValue().set(productKey,
                ProductServiceFactory.createProductDetailCacheDto(product)
        );
        return Optional.of(product);
    }


    public Optional<ProductDetailCacheDto> findProduct(Long productId) {

        String productKey = PRODUCT_KEY_PREFIX + productId;
        return Optional.ofNullable(
                productRedisTemplate.opsForValue().get(productKey)
        );
    }

    public void productStockWarmingUp(List<Product> productList) {
        productList.forEach(product -> {
            String productKey = PRODUCT_KEY_PREFIX + product.getId();
            productRedisTemplate.opsForValue().set(
                    productKey,
                    ProductServiceFactory.createProductDetailCacheDto(product)
            );
        });
    }
}
