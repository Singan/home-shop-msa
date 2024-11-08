package com.example.service.order.infrastructure.repository;

import com.example.service.order.domain.enums.OrderStatus;
import com.example.service.order.infrastructure.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByMemberId(Long customerId , Pageable pageable);

    Optional<OrderEntity> findByIdAndStatus(Long orderId , OrderStatus status);
}
