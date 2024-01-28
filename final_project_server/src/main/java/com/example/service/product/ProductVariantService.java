package com.example.service.product;

import com.example.persistence.entity.product.Product;
import com.example.persistence.entity.product.ProductVariant;
import com.example.service.CrudService;
import com.example.service.FindAllService;

import java.util.List;

public interface ProductVariantService extends CrudService<ProductVariant>, FindAllService<ProductVariant> {
    List<ProductVariant> findByProduct(Product product);
}
