package com.example.service.infrastructure.client.product;

import com.example.service.infrastructure.client.product.ProductClient;
import com.example.service.infrastructure.client.product.response.ProductDetailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.PathVariable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
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
