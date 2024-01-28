package com.example.service.cart.impl;

import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.cart.CartEntry;
import com.example.persistence.entity.product.ProductVariant;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.repository.cart.CartEntryRepository;
import com.example.persistence.repository.cart.CartRepository;
import com.example.persistence.repository.product.ProductRepository;
import com.example.persistence.repository.product.ProductVariantRepository;
import com.example.persistence.repository.user.PersonalRepository;
import com.example.service.cart.CartService;
import com.example.util.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartEntryRepository cartEntryRepository;
    private final PersonalRepository personalRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;


    private Cart findCartOfActiveUser() {
        String userName = SecurityUtil.getUsername();
        Personal personal = personalRepository.findByLogin(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Cart cart;
        Optional<Cart> optionalCart = cartRepository.findByPersonalAndActiveTrue(personal);
        if (optionalCart.isEmpty()) {
            cart = new Cart();
            cart.setPersonal(personal);
            cart = cartRepository.save(cart);
        } else {
            cart = optionalCart.get();
        }
        return cart;
    }


    @Override
    public void addProductVariantToCart(Long productVariantId, int quantity) {
        Cart cart = findCartOfActiveUser();
        ProductVariant productVariant = productVariantRepository
                .findById(productVariantId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        CartEntry cartEntry;
        Optional<CartEntry> optionalCartEntry = cartEntryRepository.findByProductVariant(productVariant);
        if (optionalCartEntry.isEmpty()) {
            cartEntry = new CartEntry();
            cartEntry.setCart(cart);
            cartEntry.setProductVariant(productVariant);
            cartEntry.setQuantity(quantity);
        } else {
            cartEntry = optionalCartEntry.get();
            int currentQuantity = cartEntry.getQuantity();
            currentQuantity = currentQuantity + quantity;
            cartEntry.setQuantity(currentQuantity);
        }
        cartEntryRepository.save(cartEntry);
    }


    @Override
    public Cart getActiveCart() {
        return findCartOfActiveUser();
    }

    @Override
    public List<CartEntry> getCartEntries() {
        Cart cart = findCartOfActiveUser();
        return cartEntryRepository.findByCart(cart);
    }
}
