package com.example.service.order.application.usecase;

import com.example.service.order.application.OrderServiceFactory;
import com.example.service.order.application.dto.response.OrderInfoToPaymentResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.exception.OrderNotFoundException;
import com.example.service.order.exception.OrderUnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoRequestUseCase {

    private final OrderRepository orderRepository;

    public OrderInfoToPaymentResponseDto orderInfoRequestToPayment(Long orderId, Long userId) {
        Order order = orderRepository.findByIdAndPending(orderId).orElseThrow(OrderNotFoundException::new);
        if(!order.getMemberId().equals(userId)){
            throw new OrderUnAuthorizedException();
        }

        return OrderServiceFactory.createOrderInfoResponseDto(order);
    }
}
