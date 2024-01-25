package com.example.facade.impl;

import com.example.api.dto.response.product.ProductPlpDto;
import com.example.facade.ProductPlpFacade;
import com.example.persistence.entity.product.ProductVariant;
import com.example.service.product.ProductService;
import com.example.service.product.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPlpFacadeImpl implements ProductPlpFacade {

    private final ProductService productService;
    private final ProductVariantService productVariantService;


    @Override
    public List<ProductPlpDto> findAll() {
        return productService.findAll()
                .stream()
                .map(product -> {
                    List<ProductVariant> variants = productVariantService.findByProduct(product);
                    return new ProductPlpDto(product,variants);
                })
                .toList();
    }
}
