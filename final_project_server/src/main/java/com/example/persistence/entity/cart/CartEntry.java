package com.example.persistence.entity.cart;

import com.example.persistence.entity.BaseEntity;
import com.example.persistence.entity.product.ProductVariant;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="cart_entries" )
public class CartEntry extends BaseEntity {

    @ManyToOne
    private Cart cart;
    @ManyToOne
    private ProductVariant productVariant;
    private Integer quantity;

    public CartEntry(){
        super();
        this.quantity=1;
    }
}