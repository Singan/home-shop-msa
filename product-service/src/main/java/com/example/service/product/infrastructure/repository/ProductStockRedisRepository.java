package com.example.service.product.infrastructure.repository;

import com.example.service.product.domain.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductStockRedisRepository {

    private final StringRedisTemplate stringRedisTemplate;
    private final static String PRODUCT_STOCK_KEY_PREFIX = "product:stock:";

    public void saveStock(Product product) {
        String stockKey = PRODUCT_STOCK_KEY_PREFIX + product.getId();
        stringRedisTemplate.opsForValue().set(stockKey, product.getStock().toString());
    }

    public Optional<Integer> findStock(Long productId) {
        String stockKey = PRODUCT_STOCK_KEY_PREFIX + productId;
        String stockValue = stringRedisTemplate.opsForValue().get(stockKey);
        return Optional.ofNullable(stockValue)
                .map(value -> {
                    try {
                        return Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        log.error("Error parsing stock value: {}", value, e);
                        return null;
                    }
                });
    }
}