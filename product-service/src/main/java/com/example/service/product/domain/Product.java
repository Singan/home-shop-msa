package com.example.service.product.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@AllArgsConstructor
public class Product {
    @NotNull
    private Long id;


    @NotNull
    @Min(0)
    private Integer stock;
    @NotNull
    private String name;

    private String description;
    @NotNull
    @Min(0)
    private Integer price;


    private LocalDateTime openDateTime;
    private LocalDateTime createdAt;

    public int increaseStock(int updateStock) {
        return stock = stock + updateStock;
    }

    public int decreaseStock(int updateStock) {
        if (stock < updateStock)
            return stock;
        return stock = stock - updateStock;
    }
    // 남은시간

    public String getLeftOpenTime() {
        LocalDateTime now = LocalDateTime.now();

        // 남은 시간을 계산
        if (openDateTime.isBefore(now)) {
            return "현재 오픈중인 상품입니다."; // 또는 다른 메시지
        }

        // 남은 시간을 직접 계산
        long days = openDateTime.getDayOfYear() - now.getDayOfYear();
        long hours = openDateTime.getHour() - now.getHour();
        long minutes = openDateTime.getMinute() - now.getMinute();
        long seconds = openDateTime.getSecond() - now.getSecond();

        // 초가 음수인 경우 처리
        if (seconds < 0) {
            seconds += 60;
            minutes--;
        }

        // 분이 음수인 경우 처리
        if (minutes < 0) {
            minutes += 60;
            hours--;
        }

        // 시가 음수인 경우 처리
        if (hours < 0) {
            hours += 24;
            days--;
        }

        return String.format("%d일 %d시간 %d분 %d초", days, hours, minutes, seconds);
    }

    public String getFormattedOpenDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return openDateTime.format(formatter);
    }

}
