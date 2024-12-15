package com.example.service.infrastructure.client.product;


import com.example.service.order.infrastructure.product.ProductClient;
import com.example.service.order.infrastructure.product.dto.ProductDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductClientTest {

    @Autowired
    private ProductClient productClient;



    @Test
    void testGetProductDetail() {
        // when
        ProductDetailDto response = productClient.getProductDetail(1L);

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(1L);
    }
}
