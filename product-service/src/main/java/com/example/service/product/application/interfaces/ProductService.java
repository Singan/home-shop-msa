package com.example.service.product.application.interfaces;

import com.example.service.product.api.dto.request.ProductAddRequest;

public interface ProductService {


    Long saveProduct(ProductAddRequest productAddRequest);
}
