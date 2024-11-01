package com.example.service.domain;

import com.example.service.domain.enums.OrderStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@Getter
public class Order {

    private Long id;
    private Integer quantity;
    private Integer totalPrice;
    private Long productId;
    private Long memberId;
    private LocalDateTime createdAt;
    private OrderStatus status;
}
