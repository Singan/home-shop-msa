package com.example.service.product.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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



}
