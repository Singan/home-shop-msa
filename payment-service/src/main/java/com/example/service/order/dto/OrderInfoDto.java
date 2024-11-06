package com.example.service.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderInfoDto {

    private Long productId;
    private Long userId;
    private Integer quantity;
    private Long orderId;
    private LocalDateTime orderTime;
    private String orderStatus;
}
