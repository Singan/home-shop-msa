package com.example.service.order.domain.repository;

import com.example.service.order.domain.Order;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {


    Order saveOrder(Order order);

    List<Order> findAllMemberId(Long userId , Pageable pageable);

    Optional<Order> findById(Long id);

    Optional<Order> findByIdAndPending(Long id);

    List<Order> findPendingOrdersOlderThan10Minutes(LocalDateTime time);

}
