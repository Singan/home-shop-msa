package com.example.service.order.infrastructure.entity;

import com.example.service.order.domain.Order;
import com.example.service.order.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Integer totalPrice;
    private Long productId;
    private Long memberId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;


    public Order toOrder() {
        return Order.builder()
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .id(id)
                .memberId(memberId)
                .productId(productId)
                .totalPrice(totalPrice)
                .quantity(quantity)
                .status(status)
                .build();
    }

    public static OrderEntity fromOrder(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .memberId(order.getMemberId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .updatedAt(order.getUpdatedAt())
                .status(order.getStatus())
                .build();
    }
}
