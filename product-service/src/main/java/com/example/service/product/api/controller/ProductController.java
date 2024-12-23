package com.example.service.product.api.controller;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.api.dto.request.ProductPageRequest;
import com.example.service.product.api.dto.response.ProductDetailResponse;
import com.example.service.product.api.dto.response.ProductListResponse;
import com.example.service.product.application.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ProductDetailResponse productDetail(@PathVariable(name = "productId") Long productId) {
        return productService.findOne(productId);
    }

    @GetMapping
    public ProductListResponse findProductList(ProductPageRequest pageRequest) {
        return productService.findAllProducts(pageRequest);
    }
}
