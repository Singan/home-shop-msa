package com.example.service.product.infrastructure.repository;

import com.example.service.product.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity , Long> {
}
