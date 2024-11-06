package com.example.service.order.application.interfaces;

import com.example.service.order.api.dto.request.OrderRequest;
import com.example.service.order.api.dto.response.OrderInfoToPaymentResponse;
import com.example.service.order.api.dto.response.OrderPlaceResponse;

public interface OrderService {

    OrderPlaceResponse placeOrder(OrderRequest orderRequest, Long userId);

    OrderInfoToPaymentResponse orderDetail(Long orderId, Long userId);
}
