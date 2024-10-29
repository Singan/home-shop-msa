package com.example.service.domain.repository;

import com.example.service.domain.Order;

public interface OrderRepository {


    Long placeOrder(Order order);
}
