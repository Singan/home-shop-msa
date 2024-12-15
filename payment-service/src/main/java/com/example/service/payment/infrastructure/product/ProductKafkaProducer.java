package com.example.service.payment.infrastructure.product;

import com.example.service.payment.infrastructure.product.dto.StockDecreaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductKafkaProducer {

    private final KafkaTemplate<String, StockDecreaseDto> kafkaTemplate;

    public void sendStockDecreaseMessage(Long productId , Integer quantity) {
        kafkaTemplate.send("product-stock-topics",
                new StockDecreaseDto(productId, quantity)
        );
    }
}
