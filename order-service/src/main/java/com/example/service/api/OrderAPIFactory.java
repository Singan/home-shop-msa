package com.example.service.api;

import com.example.service.api.dto.response.OrderPlaceResponse;
import com.example.service.application.dto.response.OrderPlaceResponseDto;
import com.example.service.domain.Order;
import com.example.service.infrastructure.client.product.response.ProductDetailResponse;

public class OrderAPIFactory {


    public static OrderPlaceResponse creOrderPlaceResponse(OrderPlaceResponseDto orderPlaceResponseDto) {

        return new OrderPlaceResponse(
                orderPlaceResponseDto.orderId(),
                orderPlaceResponseDto.orderMember(),
                orderPlaceResponseDto.productId(),
                orderPlaceResponseDto.productName(),
                orderPlaceResponseDto.productPrice(),
                orderPlaceResponseDto.totalPrice(),
                orderPlaceResponseDto.quantity(),
                orderPlaceResponseDto.orderDate()
        );
    }
}
