package com.example.api.controller.personal;


import com.example.api.dto.response.cart.CartDto;
import com.example.facade.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/personal/cart")
public class CartController {

    private final CartFacade cartFacade;

    @PostMapping
    public ResponseEntity<String> createOrUpdateCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        System.out.println("added to cart");

        cartFacade.addProductVariantToCart(productVariantId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartDto> getActiveUserCart() {
        return ResponseEntity.ok(cartFacade.getActiveCart());
    }

}
