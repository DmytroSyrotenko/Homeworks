package com.example.service;

import com.example.persistence.entity.BaseEntity;

public interface CrudService <E extends BaseEntity> {
    void create(E entity);
    E findById(Long id);
}
