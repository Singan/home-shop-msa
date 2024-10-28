package com.example.service.product.infrastructure.repository;

import com.example.service.config.JacksonConfig;
import com.example.service.config.RedisConfig;
import com.example.service.product.domain.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataRedisTest
@Import({RedisConfig.class})
class ProductRedisRepositoryTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Product testProduct;


    @BeforeEach
    void setup() throws JsonProcessingException {
        testProduct = Product.builder()
                .id(1L)
                .stock(100)
                .name("Test Product")
                .description("This is a test product.")
                .price(5000)
                .openDateTime(LocalDateTime.now().plusDays(1))
                .createdAt(LocalDateTime.now())
                .build();

        redisTemplate.opsForValue().set(String.valueOf(testProduct.getId()),
                String.valueOf(testProduct.getStock())
        );
    }

    @Test
    void testRedisProductSaveAndRetrieve() throws JsonProcessingException {
        // given
        Long productId = testProduct.getId();

        // when
        Integer savedProductStock = Integer.parseInt(
                redisTemplate.opsForValue().get(String.valueOf(productId))
        );


        // them
        assertThat(savedProductStock).isNotNull();
        assertThat(savedProductStock).isEqualTo(testProduct.getStock());
    }

    @Test
    void testProductDoesNotExist() throws JsonProcessingException {
        // given
        Long noneProductId = 999L;

        // when
        String noneProductString = redisTemplate.opsForValue().get(String.valueOf(noneProductId));
        // then
        assertThat(noneProductString).isNull();
    }
}