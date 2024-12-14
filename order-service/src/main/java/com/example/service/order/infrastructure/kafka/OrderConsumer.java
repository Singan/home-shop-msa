package com.example.service.order.infrastructure.kafka;

import com.example.service.order.domain.Order;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.exception.OrderNotFoundException;
import com.example.service.order.infrastructure.kafka.dto.OrderStatusConfirmDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "order-status-topic", groupId = "order-status-group")
    public void pendingOrderUpdate(OrderStatusConfirmDto orderStatusConfirmDto, Acknowledgment acknowledgment) {
        try {
            log.info("order Status Update 작동 : orderId : {}", orderStatusConfirmDto.id());
            Order order = orderRepository.findByIdAndPending(orderStatusConfirmDto.id())
                    .orElseThrow(OrderNotFoundException::new);
            boolean confirm = order.orderConfirm(orderStatusConfirmDto.confirm());
            orderRepository.saveOrder(order);
            if (!confirm) {
                // 환불 로직
            }
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
