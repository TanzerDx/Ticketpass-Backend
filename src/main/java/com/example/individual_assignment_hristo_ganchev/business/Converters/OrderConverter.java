package com.example.individual_assignment_hristo_ganchev.business.Converters;

import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;

public final class OrderConverter {

    private OrderConverter()
    {
        throw new IllegalStateException("Order converter");
    }

    public static Order convert(OrderEntity order) {

        return Order.builder()
                .id(order.getId())
                .concert(ConcertConverter.convert(order.getConcert()))
                .user(UserConverter.convert(order.getUser()))
                .date(order.getDate())
                .name(order.getName())
                .surname(order.getSurname())
                .address(order.getAddress())
                .phone(order.getPhone())
                .ticketNumber(order.getTicketNumber())
                .orderPrice(order.getOrderPrice())
                .paymentMethod(order.getPaymentMethod())
                .build();

    }

    public static OrderEntity convertToEntity(Order order) {

        return OrderEntity.builder()
                .id(order.getId())
                .concert(ConcertConverter.convertToEntity(order.getConcert()))
                .user(UserConverter.convertToEntity(order.getUser()))
                .date(order.getDate())
                .name(order.getName())
                .surname(order.getSurname())
                .address(order.getAddress())
                .phone(order.getPhone())
                .ticketNumber(order.getTicketNumber())
                .orderPrice(order.getOrderPrice())
                .paymentMethod(order.getPaymentMethod())
                .build();

    }
}
