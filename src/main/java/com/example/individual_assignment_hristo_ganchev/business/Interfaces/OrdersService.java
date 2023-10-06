package com.example.individual_assignment_hristo_ganchev.business.Interfaces;

import com.example.individual_assignment_hristo_ganchev.domain.Objects.Order;
import com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.domain.OrdersRelated.GetAllOrdersResponse;

public interface OrdersService {
    CreateOrderResponse createOrder(CreateOrderRequest request);

    GetAllOrdersResponse getAllOrders(Long userId);

    Order getOrder(Long id);
}
