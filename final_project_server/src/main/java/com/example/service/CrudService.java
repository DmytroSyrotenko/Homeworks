package com.example.service;

import com.example.persistence.BaseEntity;

public interface CrudService <E extends BaseEntity> {
    void create(E entity);
    E findById(Long id);
}
