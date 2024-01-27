package com.example.facade.impl;

import com.example.api.dto.response.cart.CartDto;
import com.example.api.dto.response.cart.CartEntryDto;
import com.example.facade.CartFacade;
import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.cart.CartEntry;
import com.example.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;

    @Override
    public void addProductVariantToCart(Long productVariantId, int quantity) {
        cartService.addProductVariantToCart(productVariantId, quantity);
    }

    @Override
    public CartDto getActiveCart() {
        return null;//TODO не закончено
    }

    @Override
    public List<CartEntryDto> getCartEntries() {
        return cartService.getCartEntries()
                .stream()
                .map(CartEntryDto::new).toList();
    }
}
