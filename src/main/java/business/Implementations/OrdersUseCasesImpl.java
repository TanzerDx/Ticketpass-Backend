package business.Implementations;

import business.Converters.OrderConverter;
import business.Interfaces.OrdersUseCases;
import domain.Objects.Order;
import domain.OrdersRelated.CreateOrderRequest;
import domain.OrdersRelated.CreateOrderResponse;
import domain.OrdersRelated.GetAllOrdersResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import persistence.entities.OrderEntity;
import persistence.interfaces.OrderRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class OrdersUseCasesImpl implements OrdersUseCases {

    private final OrderRepository orderRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request){
        OrderEntity savedOrder = saveNewOrder(request);

        return CreateOrderResponse.builder()
                .id(savedOrder.getId())
                .build();
    }

    @Override
    public GetAllOrdersResponse getAllOrders(Long userId){
        List<Order> allOrders = orderRepository.getAll(userId)
                .stream()
                .map(OrderConverter::convert)
                .toList();

        return GetAllOrdersResponse.builder()
                .orders(allOrders)
                .build();
    }

    @Override
    public Order getOrder(Long id){
        OrderEntity orderEntity = orderRepository.getByConcert(id);

        Order order = OrderConverter.convert(orderEntity);

        return order;
    }

    private OrderEntity saveNewOrder(CreateOrderRequest request) {
        OrderEntity order = OrderEntity.builder()
                .concertId(request.getConcertId())
                .userId(request.getUserId())
                .date(request.getDate())
                .name(request.getName())
                .surname(request.getSurname())
                .address(request.getAddress())
                .phone(request.getPhone())
                .ticketNumber(request.getTicketNumber())
                .orderPrice(request.getOrderPrice())
                .paymentMethod(request.getPaymentMethod())
                .build();

        return orderRepository.addOrder(order);
    }
}
