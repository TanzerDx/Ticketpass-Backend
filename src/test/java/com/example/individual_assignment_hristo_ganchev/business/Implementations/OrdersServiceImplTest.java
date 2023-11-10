package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.OrderConverter;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrdersServiceImplTest {

    @Test
    void getAllOrders_shouldReturnOrdersIfPresent() throws Exception {

        //Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            OrderRepository orderRepository = mock(OrderRepository.class);

            ConcertEntity concert = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000, new ArrayList<>());

            UserEntity user = new UserEntity(1L, "hristo@gmail.com", null,
                "hashedPassword", false, new ArrayList<>(), new ArrayList<>());


        List<OrderEntity> allOrders = Arrays.asList(new OrderEntity(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal", new ArrayList<>()));

            when(orderRepository.getByUserId(1l)).thenReturn(allOrders);

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository);



        // Act
            GetAllOrdersResponse sutResponse = sut.getAllOrders(1L);



        // Assert
            assertThat(sutResponse.getOrders()).isNotEmpty();
    }



    @Test
    void getAllOrders_shouldReturnAnEmptyListIfOrdersAreNotPresent() {

        //Arrange
            OrderRepository orderRepository = mock(OrderRepository.class);

            when(orderRepository.getByUserId(1l)).thenReturn(new ArrayList<>());

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository);



        // Act
            GetAllOrdersResponse sutResponse = sut.getAllOrders(1L);



        // Assert
            assertThat(sutResponse.getOrders()).isEmpty();
    }



    @Test
    void getOrder_shouldGetOrderById() throws Exception {

        //Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            OrderRepository orderRepository = mock(OrderRepository.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000, new ArrayList<>());

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000, new ArrayList<>());

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com", null,
                "hashedPassword", false, new ArrayList<>(), new ArrayList<>());

        User user = new User(1L, "hristo@gmail.com", null,
                "hashedPassword", false, new ArrayList<>(), new ArrayList<>());

        OrderEntity toReturn = new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                    "+31613532345", 3, 14.15, "Ideal", new ArrayList<>());

            Order toCompare= new Order(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                    "+31613532345", 3, 14.15, "Ideal", new ArrayList<>());

            when(orderRepository.getById(1l)).thenReturn(toReturn);

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository);



        // Act
            Order retrievedOrder = sut.getOrder(1L);



        // Assert
            assertThat(toCompare).isEqualTo(retrievedOrder);
    }
}