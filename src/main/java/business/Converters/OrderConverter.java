package business.Converters;

import domain.Objects.Order;
import persistence.entities.OrderEntity;

import java.util.Date;

public final class OrderConverter {

    public static Order convert(OrderEntity order) {
        return Order.builder()

                .id(order.getId())
                .concertId(order.getConcertId())
                .userId(order.getUserId())
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
