package com.example.service.order;

import com.example.persistence.entity.order.Order;

import java.util.List;

public interface OrderService {

    void createOrderInSystem();

    List<Order> getUserOrders();
}
