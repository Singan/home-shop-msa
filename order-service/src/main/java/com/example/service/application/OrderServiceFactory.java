package com.example.service.application;

import com.example.service.api.dto.request.OrderRequest;
import com.example.service.api.dto.response.OrderPlaceResponse;
import com.example.service.application.dto.request.OrderRequestDto;
import com.example.service.application.dto.response.OrderPlaceResponseDto;
import com.example.service.domain.Order;
import com.example.service.infrastructure.client.product.response.ProductDetailResponse;

public class OrderServiceFactory {

    public static OrderRequestDto createOrderRequestDto(OrderRequest orderRequest, Long userId) {

        return new OrderRequestDto(orderRequest.productId(), orderRequest.buyStock(), userId);
    }


    public static OrderPlaceResponseDto createOrderPlaceResponseDto(Order order,
                                                                    ProductDetailResponse productDetailResponse, Long userId) {

        return new OrderPlaceResponseDto(
                order.getId(),
                userId,
                productDetailResponse.id(),
                productDetailResponse.name(),
                productDetailResponse.price(),
                order.getTotalPrice(),
                order.getQuantity(),
                order.getCreatedAt()
        );

    }
}
