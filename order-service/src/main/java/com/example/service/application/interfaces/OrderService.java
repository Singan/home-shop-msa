package com.example.service.application.interfaces;

import com.example.service.api.dto.request.OrderRequest;
import com.example.service.api.dto.response.OrderPlaceResponse;

public interface OrderService {

    OrderPlaceResponse placeOrder(OrderRequest orderRequest, Long userId);
}
