package com.example.persistence.product;


import com.example.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString  //TODO убрать
@Table(name = "products")
public class Product extends BaseEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 4096)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "thumbnails",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_image_id")
    )
    private Set<ProductImage> productImages;

    public Product() {
        this.productImages = new HashSet<>();
    }

}
