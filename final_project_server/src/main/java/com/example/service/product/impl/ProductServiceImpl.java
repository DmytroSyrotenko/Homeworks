package com.example.service.product.impl;

import com.example.exceptions.EntityUnexistsException;
import com.example.exceptions.NotValidFieldDataException;
import com.example.persistence.product.Product;
import com.example.repository.product.ProductRepository;
import com.example.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static util.ExceptionUtil.*;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public void create(Product entity) {
        if (entity == null) {
            throw new EntityUnexistsException(ENTITY_NOT_EXISTS_EXCEPTION);
        }
        if (entity.getName() == null) {
            throw new NotValidFieldDataException(NOT_VALID_FIELD_DATA_EXCEPTION);
        }
        productRepository.save(entity);
    }

    @Override
    public Product findById(Long id) {
        if (id == null) {
            throw new NotValidFieldDataException(NOT_VALID_ID_EXCEPTION);
        }
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
