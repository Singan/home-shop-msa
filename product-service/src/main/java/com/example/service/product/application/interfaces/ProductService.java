package com.example.service.product.application.interfaces;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.api.dto.request.ProductPageRequest;
import com.example.service.product.api.dto.response.ProductDetailResponse;
import com.example.service.product.api.dto.response.ProductListResponse;

public interface ProductService {


    Long saveProduct(ProductAddRequest productAddRequest);

    ProductListResponse findAllProducts(ProductPageRequest pageRequest);

    ProductDetailResponse findOne(Long productId);
}
