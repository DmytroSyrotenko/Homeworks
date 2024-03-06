package com.example.facade;

import com.example.api.dto.response.product.ProductPdpDto;

public interface ProductPdpFacade {
    ProductPdpDto findAllByProductId(Long productId);
}
