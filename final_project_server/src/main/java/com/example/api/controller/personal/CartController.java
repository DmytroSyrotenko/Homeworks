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
    public ResponseEntity<String> createNewOrAddProductVariantToCart(@RequestParam Long productVariantId, @RequestParam(defaultValue = "1") int quantity) {
        cartFacade.addProductVariantToCart(productVariantId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartDto> getActiveUserCart() {
        return ResponseEntity.ok(cartFacade.getActiveCart());
    }


    @DeleteMapping
    public ResponseEntity<String> deleteSkuFromCart(@RequestParam Long productVariantId) {
        System.out.println("deleting"+ productVariantId);
        cartFacade.deleteProductVariantFromCart(productVariantId);
        return ResponseEntity.ok().build();
    }


}
