package com.example.service.product;

import com.example.persistence.product.Product;
import com.example.persistence.product.ProductImage;
import com.example.persistence.product.ProductVariant;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariant> findByProduct(Product product);
}
