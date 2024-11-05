package com.example.service.order.infrastructure.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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

        String luaScript = "local stock = redis.call('GET', KEYS[1]) " +
                "if not stock then " +
                "   return -1 " +  // 재고가 없는 경우
                "elseif tonumber(stock) < tonumber(ARGV[1]) then " +
                "   return -2 " +  // 재고가 부족한 경우
                "else " +
                "   redis.call('DECRBY', KEYS[1], ARGV[1]) " +
                "   return redis.call('GET', KEYS[1]) " +
                "end";

        DefaultRedisScript<Long> script = new DefaultRedisScript<>(luaScript, Long.class);
        Long updatedStock = stringRedisTemplate.execute(script, Collections.singletonList(stockKey), quantity.toString());

        if (updatedStock == null || updatedStock == -1) {
            throw new IllegalStateException("재고 정보가 없습니다. productId=" + productId);
        } else if (updatedStock == -2) {
            throw new IllegalStateException("재고가 부족합니다. 요청 수량=" + quantity);
        }

        log.info("재고 감소 완료: productId={}, 감소 수량={}, 남은 재고={}", productId, quantity, updatedStock);
        return updatedStock.intValue();
    }
}


