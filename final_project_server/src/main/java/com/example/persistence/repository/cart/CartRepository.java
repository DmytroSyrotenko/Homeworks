package com.example.persistence.repository.cart;

import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends BaseRepository<Cart> {
    Optional<Cart> findByPersonalAndActiveTrue(Personal personal);
}
