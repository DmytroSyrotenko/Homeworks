package com.example.api.controller.personal;


import com.example.api.dto.response.auth.AuthDto;
import com.example.api.dto.response.cart.CartDto;
import com.example.api.dto.response.cart.CartEntryDto;
import com.example.facade.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/personal/cart")
public class CartController {

    private final CartFacade cartFacade;

    @PostMapping
    public ResponseEntity<String> createOrUpdateCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        System.out.println("корзина создана = " );
        cartFacade.addProductVariantToCart(productVariantId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartEntryDto>> getActiveUserCart() {
        System.out.println("выкачали" + cartFacade.getCartEntries());
        return ResponseEntity.ok(cartFacade.getCartEntries());
    }
}
