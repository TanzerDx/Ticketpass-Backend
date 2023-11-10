//package com.example.individual_assignment_hristo_ganchev.persistence.implementations;
//
//import org.springframework.stereotype.Repository;
//import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
//import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.OrderRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class OrderRepositoryImpl implements OrderRepository {
//    private static long NEXT_ID = 1;
//
//    private final List<OrderEntity> savedOrders;
//
//    public OrderRepositoryImpl() {
//        this.savedOrders = new ArrayList<>();
//    }
//
//    @Override
//    public OrderEntity addOrder(OrderEntity order)
//    {
//        order.setId(NEXT_ID);
//        NEXT_ID++;
//        this.savedOrders.add(order);
//        return order;
//    }
//
//    @Override
//    public List<OrderEntity> getAll(long userId)
//    {
//        return this.savedOrders.stream()
//                .filter(orderEntity -> orderEntity.getUserId().equals(userId))
//                .toList();
//    }
//
//    @Override
//    public OrderEntity getByConcert(long concertId)
//    {
//        return this.savedOrders.stream()
//                .filter(orderEntity -> orderEntity.getConcertId().equals(concertId))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public OrderEntity getOrder(long id)
//    {
//        return this.savedOrders.stream()
//                .filter(orderEntity -> orderEntity.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//}
