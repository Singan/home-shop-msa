package com.example.service.application;

import com.example.service.api.OrderAPIFactory;
import com.example.service.api.dto.request.OrderRequest;
import com.example.service.api.dto.response.OrderPlaceResponse;
import com.example.service.application.dto.response.OrderPlaceResponseDto;
import com.example.service.application.interfaces.OrderService;
import com.example.service.application.usecase.OrderRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRequestUseCase orderRequestUseCase;
    @Override
    public OrderPlaceResponse placeOrder(OrderRequest orderRequest, Long userId) {

        OrderPlaceResponseDto orderPlaceResponseDto =
                orderRequestUseCase.placeOrder(OrderServiceFactory.createOrderRequestDto(orderRequest , userId));

        return OrderAPIFactory.creOrderPlaceResponse(orderPlaceResponseDto);
    }
}
