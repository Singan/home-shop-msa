package com.example.service.order.infrastructure.repository;

import com.example.service.order.domain.Order;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.exception.OrderNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class KafkaConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "order-status-topic" , groupId = "order-consumer")
    @Transactional
    public void pendingOrderUpdate(Long orderId){

        log.info("order Status Update 작동 : orderId : {}" , orderId);
        Order order = orderRepository.findByIdAndPending(orderId).orElseThrow(OrderNotFoundException::new);
        order.orderConfirm();
        orderRepository.saveOrder(order);

    }
}
