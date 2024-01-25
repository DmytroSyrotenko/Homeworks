package com.example.facade.impl;

import com.example.api.dto.response.product.ProductPdpDto;
import com.example.facade.ProductPdpFacade;
import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductVariant;
import com.example.service.product.ProductService;
import com.example.service.product.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductPdpFacadeImpl implements ProductPdpFacade {


    private final ProductVariantService productVariantService;
    private final ProductService productService;


    @Override
    public ProductPdpDto findAllByProductId(Long productId) {
        Product product = productService.findById(productId);
        List<ProductVariant> variants = productVariantService.findByProduct(product);
        return new ProductPdpDto(product,variants);
    }
}
