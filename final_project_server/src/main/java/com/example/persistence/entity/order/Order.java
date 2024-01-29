package com.example.persistence.entity.order;

import com.example.persistence.entity.BaseEntity;
import com.example.persistence.entity.user.Personal;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private Personal personal;

    private Integer totalPrice;
    private Date date;

    public Order() {
        this.date = new Date();
    }
}
