package com.example.service.order.infrastructure.kafka;

import com.example.service.order.infrastructure.kafka.dto.OrderStatusConfirmDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderKafkaProducer {


    private final KafkaTemplate<String, OrderStatusConfirmDto> kafkaTemplate;
    public void sendOrder(Long orderId, boolean confirm) {
        kafkaTemplate.send("order-status-topic",
                String.valueOf(orderId),
                new OrderStatusConfirmDto(orderId, confirm));
    }
}
