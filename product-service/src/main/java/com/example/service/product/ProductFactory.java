package com.example.service.product;

import com.example.service.product.domain.Product;

import java.time.LocalDateTime;

public class ProductFactory {

    static Product createProduct(String name, String description, LocalDateTime openTime , int stock , int price) {
        return Product.builder()
                .name(name)
                .openDateTime(openTime)
                .description(description)
                .price(price)
                .build();
    }
}
