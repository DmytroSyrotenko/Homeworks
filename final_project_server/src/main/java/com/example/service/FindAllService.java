package com.example.service;

import com.example.persistence.entity.BaseEntity;

import java.util.List;

public interface FindAllService<E extends BaseEntity> {

    List<E> findAll();
}
