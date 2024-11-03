package com.example.service.infrastructure.repository;

import com.example.service.infrastructure.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByMemberId(Long customerId , Pageable pageable);

}
