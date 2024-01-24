package com.example.api.dto.response.product;

import com.example.persistence.product.Product;
import com.example.persistence.product.ProductVariant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProductPdpDto {

    private String name;
    private String description;
    private String battery;
    private List<String> images;

    public ProductPdpDto(Product product, List<ProductVariant> productVariants){


    }

}
