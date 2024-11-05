package com.example.service.infrastructure.client.product;


import com.example.service.product.ProductClient;
import com.example.service.product.dto.ProductDetailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductClientTest {

    @Autowired
    private ProductClient productClient;



    @Test
    void testGetProductDetail() {
        // when
        ProductDetailResponse response = productClient.getProductDetail(1L);

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(1L);
    }
}
