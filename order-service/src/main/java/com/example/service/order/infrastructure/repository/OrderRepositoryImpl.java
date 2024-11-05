package com.example.service.order.infrastructure.repository;

import com.example.service.order.domain.Order;
import com.example.service.order.domain.repository.OrderRepository;
import com.example.service.order.infrastructure.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;


    @Override
    public Order saveOrder(Order order) {

        return orderJpaRepository.save(OrderEntity.fromOrder(order)).toOrder();
    }

    @Override
    public List<Order> findAll(Long userId, Pageable pageable) {
        return orderJpaRepository.
                findAllByMemberId(userId, pageable)
                .map(OrderEntity::toOrder)
                .stream().toList();
    }
}
