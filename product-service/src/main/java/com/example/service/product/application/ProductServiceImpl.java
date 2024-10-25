package com.example.service.product.application;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.application.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {


    @Override
    public Long saveProduct(ProductAddRequest productAddRequest) {

        return null;
    }
}
