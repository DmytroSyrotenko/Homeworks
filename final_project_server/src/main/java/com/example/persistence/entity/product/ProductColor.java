package com.example.persistence.entity.product;

import com.example.persistence.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_colors")
public class ProductColor extends BaseEntity {

    @Column(nullable = false)
    private String color;
}
