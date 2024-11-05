package com.example.config;

import com.example.service.product.application.dto.cache.ProductDetailCacheDto;
import com.example.service.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, ProductDetailCacheDto> productDetailRedisTemplate(
            RedisConnectionFactory redisConnectionFactory , ObjectMapper objectMapper) {
        RedisTemplate<String, ProductDetailCacheDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<ProductDetailCacheDto> serializer =
                new Jackson2JsonRedisSerializer<>(ProductDetailCacheDto.class);
        template.setDefaultSerializer(serializer);
        return template;
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory , ObjectMapper objectMapper) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper))
                );

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .build();
    }
}
