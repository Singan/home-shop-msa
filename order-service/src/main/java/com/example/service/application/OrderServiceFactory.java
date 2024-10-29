package com.example.service.application;

import com.example.service.api.dto.request.OrderRequest;
import com.example.service.application.dto.request.OrderRequestDto;

public class OrderServiceFactory {

    public static OrderRequestDto createOrderRequestDto(OrderRequest orderRequest , Long userId) {

        return new OrderRequestDto(orderRequest.productId(), orderRequest.buyStock() , userId);
    }
}
