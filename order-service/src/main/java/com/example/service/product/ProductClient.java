package com.example.service.product;

import com.example.service.product.dto.ProductDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "product-service", url = "${domain.url}${service.url.product-service}")
public interface ProductClient {

    @GetMapping("/products/{productId}")
    ProductDetailDto getProductDetail(@PathVariable("productId") Long productId);
}

