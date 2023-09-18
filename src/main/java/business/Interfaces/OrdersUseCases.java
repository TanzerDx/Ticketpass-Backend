package business.Interfaces;

import domain.Objects.Order;
import domain.OrdersRelated.CreateOrderRequest;
import domain.OrdersRelated.CreateOrderResponse;
import domain.OrdersRelated.GetAllOrdersResponse;

public interface OrdersUseCases {
    CreateOrderResponse createOrder(CreateOrderRequest request);

    GetAllOrdersResponse getAllOrders(Long userId);

    Order getOrder(Long id);
}
