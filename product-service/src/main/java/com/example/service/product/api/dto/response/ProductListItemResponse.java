package com.example.service.product.api.dto.response;

import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public record ProductListItemResponse(Long id,
                                      Integer stock,
                                      String name,


                                      Integer price,
                                      LocalDateTime openDateTime
) {
    public String getLeftOpenTime() {
        LocalDateTime now = LocalDateTime.now();
        Period period = Period.between(now.toLocalDate(), openDateTime.toLocalDate());
        Duration duration = Duration.between(now.toLocalTime(), openDateTime.toLocalTime());
        long days = period.getDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (duration.isNegative() && period.getDays() > 0) {
            days++;
        }
        return String.format("%d일 %d시간 %d분 %d초", days, hours, minutes, seconds);

    }
}
