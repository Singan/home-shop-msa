package com.example.service.product.infrastructure.kafka;

import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import com.example.service.product.infrastructure.entity.ProductEntity;
import com.example.service.product.infrastructure.kafka.dto.StockDecreaseDto;
import com.example.service.product.infrastructure.repository.ProductJpaRepository;
import com.example.service.product.infrastructure.repository.ProductRepositoryImpl;
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
    private final ProductRepository productRepository;

    @KafkaListener(topics = "product-stock-topics", groupId = "product-stock-group")
    @Transactional
    public void stockDecrease(StockDecreaseDto stockDecreaseDto) {
        Product product = productRepository.findOne(stockDecreaseDto.productId()).get();
        log.info("product Stock Decrease : {}" , stockDecreaseDto.productId());
        product.decreaseStock(stockDecreaseDto.quantity());
        productRepository.productSave(product);
    }
}
