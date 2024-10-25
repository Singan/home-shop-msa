package com.example.service.product.api.controller;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.api.dto.request.ProductPageRequest;
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

    public void saveProduct(ProductAddRequest productAddRequest){

    }

    @GetMapping("/{productId}")
    public void findProduct(@PathVariable(name = "productId") Long productId){

    }
    @GetMapping()
    public void findProductList(ProductPageRequest pageRequest){


    }
}
