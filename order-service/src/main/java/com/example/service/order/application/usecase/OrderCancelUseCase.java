package com.example.service.order.application.usecase;

import com.example.service.order.domain.Order;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.infrastructure.kafka.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCancelUseCase {

    private final OrderRepository orderRepository;
    private final OrderKafkaProducer orderKafkaProducer;

    public void execute(LocalDateTime now){

        // 10분 이상 지난 주문 찾기
        List<Order> orderList = orderRepository.findPendingOrdersOlderThan10Minutes(now);

        orderList.forEach(order -> {
            orderKafkaProducer.sendOrder(order.getId(), false);
        });
    }
}
