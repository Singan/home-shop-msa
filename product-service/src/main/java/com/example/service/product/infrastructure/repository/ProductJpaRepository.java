package com.example.service.product.infrastructure.repository;

import com.example.service.product.infrastructure.entity.ProductEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity , Long> {
    @Query("select p from ProductEntity p where p.id > :id and p.stock>0")
    Slice<ProductEntity> findByIdGreaterThan(@Param("id") Long id, Pageable pageable);


    @Query("SELECT p FROM ProductEntity p WHERE p.openDateTime = :now")
    List<ProductEntity> findByWarmingProductList(@Param("now") LocalDateTime now);
}
