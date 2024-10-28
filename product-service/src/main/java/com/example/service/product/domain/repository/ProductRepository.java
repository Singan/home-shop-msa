package com.example.service.product.domain.repository;

import com.example.service.product.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductRepository {


    Long productSave(Product product);


    Slice<Product> findAllProducts(Long cursor,Pageable pageable);


    Optional<Product> findOne(Long id);

    void findByWarmingProductList(LocalDateTime localDateTime);
}
