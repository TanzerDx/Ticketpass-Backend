package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.business.Converters.UserConverter;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.OrdersService;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request){
        OrderEntity savedOrder = saveNewOrder(request);

//        ConcertEntity concert = ConcertConverter.convertToEntity((request.getConcert()));
//        UserEntity user = UserConverter.convertToEntity((request.getUser()));
//
//        concert.getOrders().add(savedOrder);
//        user.getUpcomingConcerts().add(savedOrder);

        return CreateOrderResponse.builder()
                .id(savedOrder.getId())
                .build();
    }

    @Override
    public GetAllOrdersResponse getAllOrders(Long userId){
        List<Order> allOrders = orderRepository.getByUserId(userId)
                .stream()
                .map(OrderConverter::convert)
                .toList();

        return GetAllOrdersResponse.builder()
                .orders(allOrders)
                .build();
    }

    @Override
    public Order getOrder(Long id){
        OrderEntity orderEntity = orderRepository.getById(id);

        return OrderConverter.convert(orderEntity);
    }

    private OrderEntity saveNewOrder(CreateOrderRequest request) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        OrderEntity order = null;

        try {

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

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }


        return orderRepository.save(order);
    }
}
