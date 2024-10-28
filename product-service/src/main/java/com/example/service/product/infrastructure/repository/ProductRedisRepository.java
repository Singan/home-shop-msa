package com.example.service.product.infrastructure.repository;

import com.example.service.product.domain.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRedisRepository {


    private final RedisTemplate<Long, Integer> redisTemplate;

    public void productStockWarmingUp(List<Product> productList) {
        productList.forEach(product -> {
            redisTemplate.opsForValue().set(product.getId(), product.getStock());
        });
    }

}
