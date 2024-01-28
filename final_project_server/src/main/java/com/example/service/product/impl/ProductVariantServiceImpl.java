package com.example.service.product.impl;

import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductVariant;
import com.example.persistence.repository.product.ProductVariantRepository;
import com.example.service.product.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    @Override
    public List<ProductVariant> findByProduct(Product product) {
        return productVariantRepository.findAllByProduct(product);
    }

    @Override
    public void create(ProductVariant entity) {

    }

    @Override
    public ProductVariant findById(Long id) {
        return null;
    }

    @Override
    public List<ProductVariant> findAll() {
        return null;
    }
}
