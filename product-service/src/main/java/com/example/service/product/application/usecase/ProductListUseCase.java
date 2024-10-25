package com.example.service.product.application.usecase;

import com.example.service.product.ProductFactory;
import com.example.service.product.application.dto.response.ProductListDto;
import com.example.service.product.application.dto.response.ProductListItemDto;
import com.example.service.product.domain.Product;
import com.example.service.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductListUseCase {

    private final ProductRepository productRepository;


    public ProductListDto getProductList(Long cursor, int size) {

        Slice<Product> products = productRepository.findAllProducts(cursor, Pageable.ofSize(size));


        return ProductFactory.createProductListDto(products);
    }


}
