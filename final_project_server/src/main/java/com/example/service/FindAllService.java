package com.example.service;

import com.example.persistence.BaseEntity;

import java.util.List;

public interface FindAllService<E extends BaseEntity> {

    List<E> findAll();
}
