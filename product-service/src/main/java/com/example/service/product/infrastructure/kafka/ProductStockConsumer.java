package com.example.service.product.infrastructure.kafka;

import com.example.service.product.domain.Product;
import com.example.service.product.infrastructure.entity.ProductEntity;
import com.example.service.product.infrastructure.kafka.dto.StockDecreaseDto;
import com.example.service.product.infrastructure.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductStockConsumer {
    private final ProductJpaRepository productJpaRepository;

    @KafkaListener(topics = "product-stock-topics", groupId = "product-stock-group")
    @Transactional
    public void stockDecrease(StockDecreaseDto stockDecreaseDto) {
        ProductEntity productEntity = productJpaRepository.findById(stockDecreaseDto.productId()).get();
        log.info("product Stock Decrease : {}" , stockDecreaseDto.productId());
        Product product = productEntity.toProduct();
        product.decreaseStock(stockDecreaseDto.quantity());
        productJpaRepository.save(ProductEntity.fromProduct(product));
    }
}
