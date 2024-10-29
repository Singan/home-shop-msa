package com.example.service.application.interfaces;

import com.example.service.api.dto.request.OrderRequest;

public interface OrderService {

    Long orderRequest(OrderRequest orderRequest, Long userId);
}
