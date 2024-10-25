package com.example.service.product.domain.repository;

import com.example.service.product.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductRepository {


    Long productSave(Product product);


    Slice<Product> findAllProducts(Long cursor,Pageable pageable);
}
