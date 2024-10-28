package com.example.service.infrastructure.repository;

import com.example.service.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
}
