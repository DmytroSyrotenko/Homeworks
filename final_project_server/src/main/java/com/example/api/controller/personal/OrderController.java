package com.example.api.controller.personal;

import com.example.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/personal/order")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping()
    public ResponseEntity<String> createOrderFromCurrentCart() {
        orderFacade.createOrderInSystem();
        return ResponseEntity.ok().build();
    }
}
