package com.example.service.infrastructure.repository;

import com.example.service.domain.Order;
import com.example.service.domain.repository.OrderRepository;
import com.example.service.infrastructure.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;


    @Override
    public Order placeOrder(Order order) {

        return jpaOrderRepository.save(OrderEntity.fromOrder(order)).toOrder();
    }
}
