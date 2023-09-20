package com.example.individual_assignment_hristo_ganchev.persistence.interfaces;

import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;

import java.util.List;

public interface OrderRepository {
    OrderEntity addOrder(OrderEntity order);

    List<OrderEntity> getAll(long userId);

    OrderEntity getByConcert(long concertId);

    OrderEntity getOrder(long id);
}
