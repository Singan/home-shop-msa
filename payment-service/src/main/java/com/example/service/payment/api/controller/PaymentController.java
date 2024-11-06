package com.example.service.payment.api.controller;

import com.example.service.payment.api.request.PaymentConfirmRequest;
import com.example.service.payment.api.response.PaymentConfirmResponse;
import com.example.service.payment.application.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    public PaymentConfirmResponse paymentConfirm(@RequestHeader(name = "X-User-Id") Long userId
            , @RequestBody PaymentConfirmRequest paymentConfirmRequest) {
        return paymentService.paymentConfirm(userId,paymentConfirmRequest);
    }
}
