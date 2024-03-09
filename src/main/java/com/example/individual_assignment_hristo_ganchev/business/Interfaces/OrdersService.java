package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;

import java.util.List;

public interface OrdersService {
    CreateOrderResponse createOrder(CreateOrderRequest request);

    GetAllOrdersResponse getAllOrders(Long userId);

    List<Order> getOrdersForAllUsers();

    Order getOrder(Long id);
}
