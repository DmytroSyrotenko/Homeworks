package com.example.persistence.entity.product;

import com.example.persistence.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product_variants")
public class ProductVariant extends BaseEntity implements Comparable<ProductVariant> {

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Integer ssd;

    @Column(nullable = false)
    private String battery;

    @Column(nullable = false)
    private String camera;

    @Column(nullable = false)
    private String displayResolution;

    @Digits(integer = 6, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductColor productColor;
    @Override
    public int compareTo(ProductVariant o) {
        return price.compareTo(o.getPrice());
    }
}

