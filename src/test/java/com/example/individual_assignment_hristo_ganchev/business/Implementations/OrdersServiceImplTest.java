package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.ConcertRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrdersServiceImplTest {

    @Test
    public void saveNewOrder_shouldSuccessfullyBuildAnOrderEntity() throws Exception{
        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        OrderRepository orderRepositoryMock = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023-09-04T21:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");



        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023-09-04T21:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");



        OrderEntity toCompare = new OrderEntity(null,  concertEntity, userEntity, sdf.parse("2023-09-04T21:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        CreateOrderRequest request = new CreateOrderRequest( concert, user, "2023-09-04T21:00", "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        when(accessToken.getUserId()).thenReturn(1L);


        OrdersServiceImpl sut = new OrdersServiceImpl(orderRepositoryMock, accessToken);


        // Act
        OrderEntity retrievedOrder = sut.saveNewOrder(request);



        // Assert
        assertThat(toCompare).isEqualTo(retrievedOrder);
    }


    @Test
    void getAllOrders_shouldReturnOrdersIfPresent() throws Exception {

        //Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            OrderRepository orderRepository = mock(OrderRepository.class);
            AccessToken accessToken = mock(AccessToken.class);

            ConcertEntity concert = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

            UserEntity user = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        List<OrderEntity> allOrders = Arrays.asList(new OrderEntity(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal"));

            when(accessToken.getUserId()).thenReturn(1l);
            when(orderRepository.getByUserId(1l)).thenReturn(allOrders);

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);



        // Act
            GetAllOrdersResponse sutResponse = sut.getAllOrders(1L);



        // Assert
            assertThat(sutResponse.getOrders()).isNotEmpty();
    }


    @Test
    void getOrdersForAllUsers_shouldReturnOrdersForAllUsers() throws Exception {

        //Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024/02/27"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        UserEntity userEntity2 = new UserEntity(2L, "nikol@gmail.com",
                "hashedPassword", "user");



        Concert concert= new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024/02/27"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        User user2 = new User(2L, "nikol@gmail.com",
                "hashedPassword", "user");


        List<OrderEntity> toReturn = Arrays.asList(
                new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2024/01/27"), "Hristo", "Ganchev", "Woenselse Markt 18",
                        "+31613532345", 3, 14.15, "Ideal"),

                new OrderEntity(2L,  concertEntity, userEntity2, sdf.parse("2024/01/27"), "Nikol", "Genova", "Woenselse Markt 11",
                        "+31613532341", 2, 8.10, "PayPal"));

        List<Order> toCompare = Arrays.asList(
                new Order(1L,  concert, user, sdf.parse("2024/01/27"), "Hristo", "Ganchev", "Woenselse Markt 18",
                        "+31613532345", 3, 14.15, "Ideal"),

                new Order(2L,  concert, user2, sdf.parse("2024/01/27"), "Nikol", "Genova", "Woenselse Markt 11",
                        "+31613532341", 2, 8.10, "PayPal"));


        when(orderRepository.findAll()).thenReturn(toReturn);

        OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);



        // Act
        List<Order> sutResponse = sut.getOrdersForAllUsers();


        // Assert
        assertThat(sutResponse).isEqualTo(toCompare);
    }


    @Test
    void getAllOrders_shouldReturnAnEmptyListIfOrdersAreNotPresent() {

        //Arrange
            OrderRepository orderRepository = mock(OrderRepository.class);
            AccessToken accessToken = mock(AccessToken.class);

            when(accessToken.getUserId()).thenReturn(1l);
            when(orderRepository.getByUserId(1l)).thenReturn(new ArrayList<>());

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);



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
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");



        OrderEntity toReturn = new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                    "+31613532345", 3, 14.15, "Ideal");

        Order toCompare= new Order(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                    "+31613532345", 3, 14.15, "Ideal");


            when(accessToken.getUserId()).thenReturn(1l);
            when(orderRepository.getById(1l)).thenReturn(toReturn);

            OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);



        // Act
            Order retrievedOrder = sut.getOrder(1L);



        // Assert
            assertThat(toCompare).isEqualTo(retrievedOrder);
    }

    @Test
    void getOrder_shouldThrowNullPointerException_whenOrderIsCalledFromDatabase() throws Exception {

        //Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);


        NullPointerException nullPointerException = new NullPointerException();

        when(orderRepository.getById(1l)).thenThrow(nullPointerException);

        OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);


        // Act and Assert
        assertThrows(NullPointerException.class, () -> sut.getOrder(1L));
    }

    @Test
    void getAllOrders_shouldThrowAccessDeniedException_whenAccessTokenUserIDisNotEqualToPassedUserID() throws Exception {

        //Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concert = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity user = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        List<OrderEntity> allOrders = Arrays.asList(new OrderEntity(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal"));

        when(accessToken.getUserId()).thenReturn(2L);
        when(orderRepository.getByUserId(1L)).thenReturn(allOrders);

        OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);



        // Act and Assert
        assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> sut.getAllOrders(1L));
    }

    @Test
    void getOrder_shouldThrowAccessDeniedException_whenAccessTokenUserIDisNotEqualToOrderUserID() throws Exception {

        //Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");



        OrderEntity toReturn = new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        Order toCompare= new Order(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        when(accessToken.getUserId()).thenReturn(2L);
        when(orderRepository.getById(1l)).thenReturn(toReturn);

        OrdersServiceImpl sut = new OrdersServiceImpl(orderRepository, accessToken);


        // Act and Assert
        assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> sut.getOrder(1L));
    }
}