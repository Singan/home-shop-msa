package com.example.service.application;

import com.example.service.api.dto.request.OrderRequest;
import com.example.service.application.interfaces.OrderService;
import com.example.service.application.usecase.OrderRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRequestUseCase orderRequestUseCase;
    @Override
    public Long orderRequest(OrderRequest orderRequest, Long userId) {

        orderRequestUseCase.placeOrder(OrderServiceFactory.createOrderRequestDto(orderRequest , userId));

        return 0L;
    }
}
