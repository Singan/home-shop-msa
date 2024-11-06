package com.example.service.order.api;

import com.example.service.order.api.dto.response.OrderInfoToPaymentResponse;
import com.example.service.order.api.dto.response.OrderPlaceResponse;
import com.example.service.order.application.dto.response.OrderInfoToPaymentResponseDto;
import com.example.service.order.application.dto.response.OrderPlaceResponseDto;

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
                orderPlaceResponseDto.userName(),
                orderPlaceResponseDto.address(),
                orderPlaceResponseDto.phone(),
                orderPlaceResponseDto.email()
        );
    }


    public static OrderInfoToPaymentResponse creOrderInfoToPaymentResponse(OrderInfoToPaymentResponseDto orderInfoToPaymentResponseDto) {
        return new OrderInfoToPaymentResponse(
                orderInfoToPaymentResponseDto.productId(),
                orderInfoToPaymentResponseDto.orderId(),
                orderInfoToPaymentResponseDto.orderTime(),
                orderInfoToPaymentResponseDto.orderStatus()
        );


    }
}
