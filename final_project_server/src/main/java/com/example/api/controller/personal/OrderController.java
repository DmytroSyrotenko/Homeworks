package com.example.api.controller.personal;

import com.example.api.dto.response.cart.OrderDto;
import com.example.facade.CartFacade;
import com.example.facade.OrderFacade;
import com.example.persistence.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/personal/order")
public class OrderController {

    private final OrderFacade orderFacade;
    private final CartFacade cartFacade;

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getUserOrders() {
        return ResponseEntity.ok(cartFacade.getUserOrders());
    }

    @PostMapping()
    public ResponseEntity<String> createOrderFromCurrentCart() {
        orderFacade.createOrderInSystem();
        return ResponseEntity.ok().build();
    }
}
