package com.example.facade;

import com.example.api.dto.response.cart.CartDto;
import com.example.api.dto.response.cart.CartEntryDto;
import com.example.api.dto.response.cart.OrderDto;

import java.util.List;

public interface CartFacade {

    void addProductVariantToCart(Long productVariantId, int quantity);
    CartDto getActiveCart();
    List<CartEntryDto> getCartEntries();
    List<OrderDto> getUserOrders();
}
