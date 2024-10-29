package com.example.service.infrastructure.entity;

import com.example.service.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer quantity;
    private Integer price;
    private Long productId;
    private Long memberId;

    @CreatedDate
    private LocalDateTime createdAt;


    public Order toOrder() {
        return Order.builder()
                .createdAt(createdAt)
                .id(id)
                .memberId(memberId)
                .productId(productId)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public static OrderEntity fromOrder(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .memberId(order.getMemberId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();
    }
}
