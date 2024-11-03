package com.example.service.infrastructure.repository;

import com.example.service.domain.Order;
import com.example.service.domain.repository.OrderRepository;
import com.example.service.infrastructure.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;


    @Override
    public Order saveOrder(Order order) {

        return jpaOrderRepository.save(OrderEntity.fromOrder(order)).toOrder();
    }

    @Override
    public List<Order> findAll(Long userId, Pageable pageable) {
        return jpaOrderRepository.
                findAllByMemberId(userId, pageable)
                .map(OrderEntity::toOrder)
                .stream().toList();
    }
}
