package com.example.service.product.application.dto.cache;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDetailCacheDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String openDateTime;
}
