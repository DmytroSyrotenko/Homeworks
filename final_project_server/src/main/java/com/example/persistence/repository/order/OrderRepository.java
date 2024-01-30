package com.example.persistence.repository.order;

import com.example.persistence.entity.order.Order;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.repository.BaseRepository;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findAllByPersonal(Personal personal);

}
