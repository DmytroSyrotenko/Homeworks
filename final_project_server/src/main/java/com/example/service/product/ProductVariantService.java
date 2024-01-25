package com.example.service.product;

import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariant> findByProduct(Product product);
}
