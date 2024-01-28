package com.example.persistence.entity.order;

import com.example.persistence.entity.BaseEntity;
import com.example.persistence.entity.cart.Cart;
import com.example.persistence.entity.cart.CartEntry;
import com.example.persistence.entity.product.ProductVariant;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "order_entries")
public class OrderEntry extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private ProductVariant productVariant;

    private Integer quantity;
    private Integer sum;
    private Date date;


    public OrderEntry(CartEntry cartEntry) {
        this.date = new Date();
        this.sum = cartEntry.getProductVariant().getPrice().intValueExact();
        this.quantity= cartEntry.getQuantity();

    }
}
