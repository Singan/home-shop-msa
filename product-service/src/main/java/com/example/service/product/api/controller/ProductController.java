package com.example.service.product.api.controller;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.api.dto.request.ProductPageRequest;
import com.example.service.product.api.dto.response.ProductListResponse;
import com.example.service.product.application.dto.response.ProductListDto;
import com.example.service.product.application.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping

    public Long saveProduct(@RequestBody ProductAddRequest productAddRequest) {
        return productService.saveProduct(productAddRequest);
    }

    @GetMapping("/{productId}")
    public void findProduct(@PathVariable(name = "productId") Long productId) {

    }

    @GetMapping
    public ProductListResponse findProductList(ProductPageRequest pageRequest) {
        return productService.findAllProducts(pageRequest);
    }
}
