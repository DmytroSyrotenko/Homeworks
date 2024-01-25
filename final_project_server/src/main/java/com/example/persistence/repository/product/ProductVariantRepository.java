package com.example.persistence.repository.product;

import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductVariant;
import com.example.persistence.repository.BaseRepository;

import java.util.List;

public interface ProductVariantRepository extends BaseRepository<ProductVariant> {
    List<ProductVariant> findAllByProduct(Product product);
}
