package com.example.service.order.application;

import com.example.service.order.api.dto.request.OrderRequest;
import com.example.service.order.application.dto.request.OrderRequestDto;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.infrastructure.client.product.response.ProductDetailResponse;

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
