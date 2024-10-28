package com.example.service.product.api.dto.response;

import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public record ProductListItemResponse(Long id,
                                      Integer stock,
                                      String name,


                                      Integer price,
                                      String openDateTime,
                                      String leftTime
) {
}
