package com.example.service.payment.infrastructure.repository;

import com.example.service.payment.domain.Payment;
import com.example.service.payment.domain.repository.PGRepository;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class PGRepositoryImpl implements PGRepository {
    private final Random rand = new Random();

    @Override
    public boolean paymentGatewayToSend(Payment payment) {
        return rand.nextInt(100000) + 1 < 100000;
    }

}
