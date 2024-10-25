package com.example.service.product.application.interfaces;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.api.dto.request.ProductPageRequest;
import com.example.service.product.api.dto.response.ProductListResponse;
import com.example.service.product.application.dto.response.ProductListDto;
import com.example.service.product.domain.Product;

public interface ProductService {


    Long saveProduct(ProductAddRequest productAddRequest);

    ProductListResponse findAllProducts(ProductPageRequest pageRequest);
}
