package com.example.persistence.entity.cart;

import com.example.persistence.entity.BaseEntity;
import com.example.persistence.entity.user.Personal;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @ManyToOne
    private Personal personal;

    private Boolean active;

    public Cart(){
        this.active=true;
    }

}
