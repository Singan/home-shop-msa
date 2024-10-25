package com.example.service.product.api.dto.response;

import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;

public record ProductListItemResponse(Long id,
                                      Integer stock,
                                      String name,


                                      Integer price,
                                      LocalDateTime openDateTime
) {
    public Duration getLeftOpenTime(){
        return Duration.between(LocalDateTime.now(), openDateTime);
    };
}
