package com.example.service;

import com.example.persistence.product.Product;

public interface ProductService extends CrudService<Product>, FindAllService<Product> {
}
