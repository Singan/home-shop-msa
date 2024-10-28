package com.example.service.api.controller;

import com.example.service.api.request.OrderRequest;
import com.example.service.application.interfaces.OrderService;
import com.example.service.infrastructure.client.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    private final ProductClient productClient;
    @PostMapping
    public void orderRequest(@RequestBody OrderRequest orderRequest , @RequestHeader("X-User-Id") Long userId) {
        orderService.orderRequest(orderRequest);
        System.out.println(productClient.getProductDetail(orderRequest.productId()).id());
    }
}
