package com.example.hw13.service;

import com.example.hw13.entity.BaseEntity;

import java.util.Collection;

public interface CrudService <E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
