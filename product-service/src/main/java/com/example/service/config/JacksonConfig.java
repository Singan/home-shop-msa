package com.example.service.config;

import com.example.service.product.application.dto.cache.ProductDetailCacheDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PolymorphicTypeValidator validator = BasicPolymorphicTypeValidator
                .builder()
                .allowIfBaseType(ProductDetailCacheDto.class)
                .build();
        objectMapper.activateDefaultTyping(validator , ObjectMapper.DefaultTyping.NON_FINAL);
        return objectMapper;
    }
}
