package com.example.service.product.application.scheduler;

import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductScheduler {


    private final ProductRepository productRepository;


    private static final int CACHE_WARMUP_TIME_MINUTES = 10;

    @Scheduled(cron = "0 * * * * *")
    public void warmingStock() {

        LocalDateTime cacheWarmupTime = calculateCacheWarmupTime();
        log.info("Warming stock...  {}" , cacheWarmupTime);
        productRepository.findByWarmingProductList(cacheWarmupTime);

        log.info("Warming stock done.");
    }

    private LocalDateTime calculateCacheWarmupTime() {
        return LocalDateTime.now()
                .plusMinutes(CACHE_WARMUP_TIME_MINUTES)
                .withSecond(0)
                .withNano(0);
    }
}
