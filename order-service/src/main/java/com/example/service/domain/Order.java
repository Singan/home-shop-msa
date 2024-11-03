package com.example.service.domain;

import com.example.service.domain.enums.OrderStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @Min(0)
    @NotNull
    private Integer quantity;
    @NotNull
    @Min(0)
    private Integer totalPrice;
    @NotNull
    private Long productId;
    @NotNull
    private Long memberId;
    @NotNull
    private LocalDateTime createdAt;
    private OrderStatus status;
}
