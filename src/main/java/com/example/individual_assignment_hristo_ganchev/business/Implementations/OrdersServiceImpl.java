package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.OrdersService;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;
    private AccessToken requestAccessToken;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request){
        OrderEntity savedOrder = saveNewOrder(request);

        return CreateOrderResponse.builder()
                .id(savedOrder.getId())
                .build();
    }

    @Override
    public GetAllOrdersResponse getAllOrders(Long userId){

        if (!requestAccessToken.getUserId().equals(userId))
        {
            throw new AccessDeniedException("Unauthorized access");
        }

        List<Order> allOrders = orderRepository.getByUserId(userId)
                .stream()
                .map(OrderConverter::convert)
                .toList();

        return GetAllOrdersResponse.builder()
                .orders(allOrders)
                .build();

    }

    @Override
    public List<Order> getOrdersForAllUsers(){

        return orderRepository.findAll()
                .stream()
                .map(OrderConverter::convert)
                .toList();
    }

    @Override
    public Order getOrder(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);

        if (!requestAccessToken.getUserId().equals(orderEntity.getUser().getId())
                && !requestAccessToken.getRoles().contains("admin")
                && !requestAccessToken.getRoles().contains("manager"))
        {
            throw new AccessDeniedException("Unauthorized access");
        }

        return OrderConverter.convert(orderEntity);
    }

    protected OrderEntity saveNewOrder(CreateOrderRequest request) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        OrderEntity order = null;

        if (request.getTicketNumber() > request.getConcert().getTicketsRemaining())
        {
            throw new IllegalStateException("Tickets desired are more than the available amount!");
        }

        try {

            if (requestAccessToken.getUserId().equals(request.getUser().getId()))
            {
                order = OrderEntity.builder()
                        .concert(ConcertConverter.convertToEntity((request.getConcert())))
                        .user(UserConverter.convertToEntity((request.getUser())))
                        .date(sdf.parse(String.valueOf(request.getDate())))
                        .name(request.getName())
                        .surname(request.getSurname())
                        .address(request.getAddress())
                        .phone(request.getPhone())
                        .ticketNumber(request.getTicketNumber())
                        .orderPrice(request.getOrderPrice())
                        .paymentMethod(request.getPaymentMethod())
                        .build();

                orderRepository.save(order);
            }

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }


        return order;
    }
}
