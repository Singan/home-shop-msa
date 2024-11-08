package com.example.service.order.application.usecase;

import com.example.service.order.application.OrderServiceFactory;
import com.example.service.order.application.dto.response.OrderInfoToPaymentResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoRequestUseCase {

    private final OrderRepository orderRepository;

    public OrderInfoToPaymentResponseDto orderInfoRequestToPayment(Long orderId, Long userId) {
        Order order = orderRepository.findByIdAndPending(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        if(order.getMemberId() != userId){
            throw new RuntimeException("요청자와 주문자가 일치하지 않습니다.");
        }

        return OrderServiceFactory.createOrderInfoResponseDto(order);
    }
}
