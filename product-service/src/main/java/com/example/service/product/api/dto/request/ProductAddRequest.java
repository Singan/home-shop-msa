package com.example.service.product.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductAddRequest(

        @NotNull
        @Min(0)
        Integer stock,
        @NotNull
        String name,
        String description,
        @NotNull
        @Min(0)
        Integer price
) {
    public LocalDateTime getOpenTime() {
        return LocalDateTime.now().plusMinutes((int) (Math.random() * 20) + 5).withNano(0);
    }
}
