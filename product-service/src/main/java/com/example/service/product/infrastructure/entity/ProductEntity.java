package com.example.service.product.infrastructure.entity;

import com.example.service.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull
    private Integer stock;
    @NotNull
    private String name;

    private String description;
    @NotNull
    private Integer price;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime openDateTime;


    public Product toProduct() {
        return Product.builder()
                .id(id)
                .price(price)
                .stock(stock)
                .name(name)
                .description(description)
                .createdAt(createdAt).openDateTime(openDateTime)
                .build();
    }

    public static ProductEntity fromProduct(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .stock(product.getStock())
                .openDateTime(product.getOpenDateTime())
                .createdAt(product.getCreatedAt())
                .build();
    }

}
