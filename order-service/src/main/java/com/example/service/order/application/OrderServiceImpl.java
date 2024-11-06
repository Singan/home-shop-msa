package com.example.service.order.application;

import com.example.service.order.api.OrderAPIFactory;
import com.example.service.order.api.dto.response.OrderInfoResponse;
import com.example.service.order.api.dto.request.OrderRequest;
import com.example.service.order.api.dto.response.OrderPlaceResponse;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;
import com.example.service.order.application.interfaces.OrderService;
import com.example.service.order.application.usecase.OrderInfoRequestUseCase;
import com.example.service.order.application.usecase.OrderRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRequestUseCase orderRequestUseCase;

    private final OrderInfoRequestUseCase orderInfoRequestUseCase;
    @Override
    public OrderPlaceResponse placeOrder(OrderRequest orderRequest, Long userId) {

        OrderPlaceResponseDto orderPlaceResponseDto =
                orderRequestUseCase.placeOrder(OrderServiceFactory.createOrderRequestDto(orderRequest , userId));

        return OrderAPIFactory.creOrderPlaceResponse(orderPlaceResponseDto);
    }

    @Override
    public OrderInfoResponse orderDetail(Long orderId, Long userId) {
        return orderInfoRequestUseCase;
    }
}
