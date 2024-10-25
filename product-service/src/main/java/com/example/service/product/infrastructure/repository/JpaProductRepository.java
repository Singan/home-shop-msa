package com.example.service.product.infrastructure.repository;

import com.example.service.product.infrastructure.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity , Long> {


    Slice<ProductEntity> findByIdGreaterThan(Long id, Pageable pageable);
}
