package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.*;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.TicketRepository;
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

class TicketsServiceImplTest {

    @Test
    public void addTickets_shouldReturnResponseWithID1_whenTicketsAreAddedToTheDatabase() throws Exception {

        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);


        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        Order order = new Order(1L,  concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 1, 14.15, "Ideal");

        OrderEntity orderEntity = new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 1, 14.15, "Ideal");

        List<TicketEntity> tickets = Arrays.asList(new TicketEntity(1L, orderEntity ,
                "QR", "Hristo Ganchev", "Standing", null, null));


        AddTicketsRequest request = new AddTicketsRequest(order);


        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, accessToken);


        when(sut.saveTickets(orderEntity)).thenReturn(tickets);



        // Act
        AddTicketsResponse response = sut.addTickets(request);


        // Assert
        assertThat(response.getTickets()).isEqualTo(tickets);
    }

    @Test
    void getTickets_shouldReturnTicketsIfPresent() throws Exception {

     // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concert = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity user = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        OrderEntity order = new OrderEntity(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        List<TicketEntity> tickets = Arrays.asList(new TicketEntity(1L, order ,
                "QR", "Hristo Ganchev", "Standing", null, null));

        when(accessToken.getUserId()).thenReturn(1l);
        when(ticketRepository.getByOrderId(1L)).thenReturn(tickets);
        when(orderRepository.getById(1L)).thenReturn(order);

        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, accessToken);



    // Act
        List<Ticket> receivedTickets = sut.getTickets(1L);



    // Assert
        assertThat(receivedTickets).isNotEmpty();
    }

    @Test
    void getTickets_shouldReturnAnEmptyListIfTicketsAreNotPresent() throws Exception {

    // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);


        ConcertEntity concert = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity user = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        OrderEntity order = new OrderEntity(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        when(accessToken.getUserId()).thenReturn(1l);
        when(ticketRepository.getByOrderId(1L)).thenReturn(new ArrayList<>());
        when(orderRepository.getById(1L)).thenReturn(order);


        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, accessToken);



    // Act
        List<Ticket> receivedTickets = sut.getTickets(1L);



    // Assert
        assertThat(receivedTickets).isEmpty();

    }

    @Test
    void getTickets_shouldThrowNullPointerException() throws Exception {

        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);


        NullPointerException nullPointerException = new NullPointerException();

        when(accessToken.getUserId()).thenReturn(1l);
        when(ticketRepository.getByOrderId(1L)).thenThrow(nullPointerException);

        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, accessToken);


        // Act and Assert
        assertThrows(NullPointerException.class, () -> sut.getTickets(1L));
    }

    @Test
    void getTickets_shouldThrowAccessDeniedException_whenAccessTokenUserIDisNotEqualToOrderUserID() throws Exception {

        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);
        AccessToken accessToken = mock(AccessToken.class);

        ConcertEntity concert = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity user = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        OrderEntity order = new OrderEntity(1L,  concert, user, sdf.parse("2023/09/02"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        List<TicketEntity> tickets = Arrays.asList(new TicketEntity(1L, order ,
                "QR", "Hristo Ganchev", "Standing", null, null));

        when(accessToken.getUserId()).thenReturn(2L);
        when(ticketRepository.getByOrderId(1L)).thenReturn(tickets);
        when(orderRepository.getById(1L)).thenReturn(order);

        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, accessToken);


        // Act and Assert
        assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> sut.getTickets(1L));
    }


}