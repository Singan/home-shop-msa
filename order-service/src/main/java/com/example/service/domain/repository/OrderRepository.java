package com.example.service.domain.repository;

import com.example.service.domain.Order;

public interface OrderRepository {


    Order placeOrder(Order order);
}
