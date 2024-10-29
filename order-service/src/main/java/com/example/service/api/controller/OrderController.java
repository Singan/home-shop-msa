package com.example.service.api.controller;

import com.example.service.api.dto.request.OrderRequest;
import com.example.service.application.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Long orderRequest(@RequestBody @Valid OrderRequest orderRequest , @RequestHeader("X-User-Id") Long userId) {
        return orderService.orderRequest(orderRequest , userId);
    }
}
