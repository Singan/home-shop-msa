package com.example.service.product.application.dto.cache;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

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
