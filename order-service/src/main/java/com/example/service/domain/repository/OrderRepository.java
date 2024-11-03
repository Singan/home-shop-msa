package com.example.service.domain.repository;

import com.example.service.domain.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {


    Order saveOrder(Order order);

    List<Order> findAll(Long userId , Pageable pageable);
}
