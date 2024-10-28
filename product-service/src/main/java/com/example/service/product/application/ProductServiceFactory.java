package com.example.service.product.application;

import com.example.service.product.application.dto.response.ProductDetailDto;
import com.example.service.product.application.dto.response.ProductListDto;
import com.example.service.product.application.dto.response.ProductListItemDto;
import com.example.service.product.domain.Product;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceFactory {


    public static ProductListDto createProductListDto(Slice<Product> products) {

        // products에서 제품 정보를 DTO 리스트로 변환
        List<ProductListItemDto> productDtos = products.getContent().stream()
                .map(ProductServiceFactory::createProductListItemDto)
                .collect(Collectors.toList());
        // 다음 커서 설정: 다음 페이지가 있는 경우, 다음 커서를 마지막 제품의 ID로 설정
        Long nextCursor = products.hasNext()
                ? productDtos.getLast().id()
                : null;

        return new ProductListDto(productDtos, nextCursor);
    }


    private static ProductListItemDto createProductListItemDto(Product product) {
        return new ProductListItemDto(product.getId(),
                product.getStock(),
                product.getName(), product.getPrice(), product.getFormattedOpenDateTime() , product.getLeftOpenTime());
    }
    public static ProductDetailDto createProductDetailDto(Product product) {
        return new ProductDetailDto(
                product.getId(), product.getStock(), product.getName(), product.getPrice(),
                product.getFormattedOpenDateTime(), product.getLeftOpenTime(),product.getDescription()
        );
    }

}
