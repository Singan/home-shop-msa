package com.example.service.order.domain.repository;

import com.example.service.order.domain.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {


    Order saveOrder(Order order);

    List<Order> findAll(Long userId , Pageable pageable);
}
