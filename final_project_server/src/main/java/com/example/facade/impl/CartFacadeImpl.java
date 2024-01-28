package com.example.facade.impl;

import com.example.api.dto.response.cart.CartDto;
import com.example.api.dto.response.cart.CartEntryDto;
import com.example.facade.CartFacade;
import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.product.Product;
import com.example.service.cart.CartService;
import com.example.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;
    private final ProductService productService;


    @Override
    public void addProductVariantToCart(Long productVariantId, int quantity) {
        cartService.addProductVariantToCart(productVariantId, quantity);
    }

    @Override
    public CartDto getActiveCart() {
        return new CartDto(cartService.getActiveCart(),getCartEntries());
    }

    @Override// TODO похоже на дубль -не обязательно етот метод в фасаде а достаточно в сервисе хотя тут перегонка в дто
    public List<CartEntryDto> getCartEntries() {
        return cartService.getCartEntries()
                .stream()
                .map(cartEntry -> {
                    Product product = productService.findById(cartEntry.getProductVariant().getProduct().getId());
                    return new CartEntryDto(cartEntry, product);
                })
                .toList();
    }
}
