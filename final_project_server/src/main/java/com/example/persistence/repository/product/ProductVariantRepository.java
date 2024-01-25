package com.example.repository.product;

import com.example.persistence.product.Product;
import com.example.persistence.product.ProductVariant;
import com.example.repository.BaseRepository;

import java.util.List;

public interface ProductVariantRepository extends BaseRepository<ProductVariant> {
    List<ProductVariant> findAllByProduct(Product product);
}
