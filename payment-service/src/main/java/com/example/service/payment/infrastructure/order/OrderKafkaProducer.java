package com.example.service.payment.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderKafkaProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;


    public void sendOrder(Long orderId) {
        kafkaTemplate.send("order-status-topic", orderId);
    }

}
