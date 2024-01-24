package com.example.facade;

import com.example.api.dto.response.product.ProductPdpDto;
import com.example.api.dto.response.product.ProductVariantDto;

import java.util.List;

public interface ProductPdpFacade {
    ProductPdpDto findAllByProductId(Long productId);
}
