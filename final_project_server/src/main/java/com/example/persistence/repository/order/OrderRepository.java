package com.example.persistence.repository.order;

import com.example.persistence.entity.order.Order;
import com.example.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order> {
}
