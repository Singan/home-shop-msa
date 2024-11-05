package com.example.service.order.api.controller;

import com.example.service.order.api.dto.request.OrderRequest;
import com.example.service.order.api.dto.response.OrderPlaceResponse;
import com.example.service.order.application.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderPlaceResponse placeOrder(@RequestBody @Valid OrderRequest orderRequest , @RequestHeader("X-User-Id") Long userId) {
        return orderService.placeOrder(orderRequest , userId);
    }
}
