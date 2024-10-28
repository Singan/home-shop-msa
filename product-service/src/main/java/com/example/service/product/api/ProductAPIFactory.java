package com.example.service.product.api;

import com.example.service.product.api.dto.response.ProductDetailResponse;
import com.example.service.product.api.dto.response.ProductListItemResponse;
import com.example.service.product.api.dto.response.ProductListResponse;
import com.example.service.product.application.dto.response.ProductDetailDto;
import com.example.service.product.application.dto.response.ProductListDto;
import com.example.service.product.application.dto.response.ProductListItemDto;
import com.example.service.product.domain.Product;

public class ProductAPIFactory {

    public static ProductDetailResponse createProductDetailResponse(ProductDetailDto productDetailDto) {
        return new ProductDetailResponse(
                productDetailDto.id(), productDetailDto.stock(), productDetailDto.name(), productDetailDto.price(),
                productDetailDto.openDateTime(), productDetailDto.leftTime(), productDetailDto.description()
        );
    }

    private static ProductListItemResponse ProductListItemResponse(ProductListItemDto productListItemDto) {
        return new ProductListItemResponse(
                productListItemDto.id(),
                productListItemDto.stock(),
                productListItemDto.name(),
                productListItemDto.price(),
                productListItemDto.openDateTime(),
                productListItemDto.leftTime());
    }

    public static ProductListResponse createProductListResponse(ProductListDto productListDto) {
        return new ProductListResponse(
                productListDto.nextCursor(),
                productListDto.products().stream().map(ProductAPIFactory::ProductListItemResponse).toList()
        );
    }
}
