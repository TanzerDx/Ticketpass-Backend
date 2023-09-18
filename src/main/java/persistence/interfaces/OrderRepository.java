package persistence.interfaces;

import persistence.entities.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    OrderEntity addOrder(OrderEntity order);

    List<OrderEntity> getAll(long userId);

    OrderEntity getByConcert(long concertId);

    OrderEntity getOrder(long id);
}
