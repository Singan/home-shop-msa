package com.example.service.config;

import com.example.service.payment.infrastructure.product.dto.StockDecreaseDto;
import jakarta.validation.Valid;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Bean
    public KafkaTemplate<String, StockDecreaseDto> kafkaTemplate1(ProducerFactory<String, StockDecreaseDto> producerFactory1) {
        return new KafkaTemplate<>(producerFactory1);
    }

    @Bean
    public ProducerFactory<String, StockDecreaseDto> producerFactory1() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.service.payment.infrastructure.product.dto");
        configProps.put(JsonDeserializer.TYPE_MAPPINGS, "stockDecreaseDto:com.example.service.payment.infrastructure.product.dto.StockDecreaseDto");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Long> kafkaTemplate2(ProducerFactory<String, Long> producerFactory2) {
        return new KafkaTemplate<>(producerFactory2);
    }

    @Bean
    public ProducerFactory<String, Long> producerFactory2() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

}
