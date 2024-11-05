package com.example.service.order.infrastructure.repository;

import com.example.service.order.infrastructure.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByMemberId(Long customerId , Pageable pageable);

}
