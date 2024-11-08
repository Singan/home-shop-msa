package com.example.service.order.domain;

import com.example.service.order.application.dto.request.OrderRequestDto;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderValidator {
    public boolean isValidOrder(ProductDetailDto product, OrderRequestDto orderRequest) {
        return isProductOpen(product.openDateTime()) &&
                hasSufficientStock(product.stock(), orderRequest.buyStock());
    }

    private boolean isProductOpen(String openDateTime) {
        LocalDateTime openDate = LocalDateTime.parse(openDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return LocalDateTime.now().isAfter(openDate);
    }

    private boolean hasSufficientStock(int stock, int requestedStock) {
        return stock >= requestedStock;
    }

}

