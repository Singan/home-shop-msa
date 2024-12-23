package com.example.service.order.application;

import com.example.service.order.api.dto.request.OrderRequest;
import com.example.service.order.application.dto.request.OrderRequestDto;
import com.example.service.order.application.dto.response.OrderInfoToPaymentResponseDto;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;
import com.example.service.order.domain.Order;
import com.example.service.order.infrastructure.member.dto.MemberInfoDto;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;

public class OrderServiceFactory {

    public static OrderRequestDto createOrderRequestDto(OrderRequest orderRequest, Long userId) {

        return new OrderRequestDto(orderRequest.productId(), orderRequest.buyStock(), userId);
    }


    public static OrderPlaceResponseDto createOrderPlaceResponseDto(Order order,
                                                                    ProductDetailDto productDetailDto, Long userId, MemberInfoDto memberInfoDto) {

        return new OrderPlaceResponseDto(
                order.getId(),
                userId,
                productDetailDto.id(),
                productDetailDto.name(),
                productDetailDto.price(),
                order.getTotalPrice(),
                order.getQuantity(),
                memberInfoDto.name(),
                memberInfoDto.address(),
                memberInfoDto.phone(),
                memberInfoDto.email()
        );
    }

    public static OrderInfoToPaymentResponseDto createOrderInfoResponseDto(Order order) {
        return new OrderInfoToPaymentResponseDto(
                order.getId(),
                order.getProductId(),
                order.getMemberId(),
                order.getQuantity(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getTotalPrice()
        );
    }
}
