package com.example.persistence.repository.cart;

import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.cart.CartEntry;
import com.example.persistence.entity.product.ProductVariant;
import com.example.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartEntryRepository extends BaseRepository<CartEntry> {

    List<CartEntry> findByCart(Cart cart);
    Optional<CartEntry> findByProductVariantAndCart(ProductVariant productVariant,Cart cart);
}
