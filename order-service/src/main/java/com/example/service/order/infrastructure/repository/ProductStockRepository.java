package com.example.service.order.infrastructure.repository;

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
public class ProductStockRepository {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String PRODUCT_STOCK_KEY_PREFIX = "product:stock:";

    public Integer decreaseStock(Long productId, Integer quantity) {
        String stockKey = PRODUCT_STOCK_KEY_PREFIX + productId;
        Long updatedStock = stringRedisTemplate.opsForValue().decrement(stockKey, quantity);

        if (updatedStock == null) {
            throw new IllegalStateException("재고 정보가 없습니다. productId=" + productId);
        } else if (updatedStock < 0) {
            // 재고가 부족한 경우 예외 발생 및 롤백을 위해 재고 복구
            stringRedisTemplate.opsForValue().increment(stockKey, quantity);
            throw new IllegalStateException("재고가 부족합니다. 요청 수량=" + quantity + ", 현재 재고=" + (updatedStock + quantity));
        }

        log.info("재고 감소 완료: productId={}, 감소 수량={}, 남은 재고={}", productId, quantity, updatedStock);
        return updatedStock.intValue();
    }
}


