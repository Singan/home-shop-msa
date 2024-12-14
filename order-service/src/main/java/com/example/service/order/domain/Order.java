package com.example.service.order.domain;

import com.example.service.order.domain.enums.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
    private LocalDateTime updatedAt;
    private OrderStatus status;


    public boolean orderConfirm(boolean confirm) {
        if (confirm) {
            status = OrderStatus.CONFIRM;
        }else{
            status = OrderStatus.CANCELLED;
        }
        return confirm;
    }
}
