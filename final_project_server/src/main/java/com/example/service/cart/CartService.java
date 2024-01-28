package com.example.service.cart;

import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.cart.CartEntry;
import com.example.service.CrudService;

import java.util.List;

public interface CartService {

    void addProductVariantToCart(Long productVariantId, int quantity);
     Cart getActiveCart();
    List<CartEntry> getActiveCartEntries();
}
