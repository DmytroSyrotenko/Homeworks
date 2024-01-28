package com.example.facade.impl;

import com.example.facade.OrderFacade;
import com.example.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    @Override
    public void createOrderInSystem() {
        orderService.createOrderInSystem();
    }
}
