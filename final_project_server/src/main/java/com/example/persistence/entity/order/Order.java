package com.example.persistence.entity.order;

import com.example.persistence.entity.BaseEntity;
import com.example.persistence.entity.user.Personal;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    Date date;

    @ManyToOne
    private Personal personal;


    public Order() {
        this.date = new Date();
    }
}
