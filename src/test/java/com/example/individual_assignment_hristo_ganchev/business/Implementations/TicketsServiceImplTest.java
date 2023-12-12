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
import static org.mockito.Mockito.*;

class TicketsServiceImplTest {

    @Test
    void saveTickets_shouldSuccessfullyBuildTicketEntities() throws Exception {

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
                "+31613532345", 2, 14.15, "Ideal");


        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, accessToken);


        // Act
        List<TicketEntity> retrievedTickets = sut.saveTickets(order);



        // Assert
        assertThat(retrievedTickets).isNotEmpty();
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