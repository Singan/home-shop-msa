package com.example.service.product.application;

import com.example.service.product.api.dto.request.ProductAddRequest;
import com.example.service.product.api.dto.request.ProductPageRequest;
import com.example.service.product.api.dto.response.ProductListResponse;
import com.example.service.product.application.dto.response.ProductListDto;
import com.example.service.product.application.dto.response.ProductListItemDto;
import com.example.service.product.application.interfaces.ProductService;
import com.example.service.product.application.usecase.ProductListUseCase;
import com.example.service.product.application.usecase.ProductSaveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductSaveUseCase productSaveUseCase;
    private final ProductListUseCase productListUseCase;

    @Override
    public Long saveProduct(ProductAddRequest productAddRequest) {

        return productSaveUseCase.productSave(productAddRequest);
    }

    @Override
    public ProductListResponse findAllProducts(ProductPageRequest pageRequest) {

        ProductListDto productListDto = productListUseCase.getProductList(pageRequest.cursor(), pageRequest.size());
        return new ProductListResponse(
                productListDto.nextCursor(),
                productListDto.products().stream().map(ProductListItemDto::getProductListItem).toList()
        );
    }


}
