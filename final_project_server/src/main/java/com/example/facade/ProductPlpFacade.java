package com.example.facade;

import com.example.api.dto.response.product.ProductPlpDto;

import java.util.List;

public interface ProductPlpFacade {

    List<ProductPlpDto> findAll();
}
