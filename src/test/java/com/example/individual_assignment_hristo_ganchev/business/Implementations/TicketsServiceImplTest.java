package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.UpdateTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.ConcertRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.TicketRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketsServiceImplTest {

    @Test
    void getTickets_shouldReturnTicketsIfPresent() throws Exception {

     // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        ConcertRepository concertRepository = mock(ConcertRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);

        List<TicketEntity> tickets = Arrays.asList(new TicketEntity(1L, 1L, 1L,
                "QR", "Hristo Ganchev", "Kim Petras", "TivoliVredenburg",
                sdf.parse("2024/02/28"), "Utrecht", "Standing", null, null));

        when(ticketRepository.getByOrderId(1L)).thenReturn(tickets);

        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, concertRepository);



    // Act
        List<Ticket> receivedTickets = sut.getTickets(1L);



    // Assert
        assertThat(receivedTickets).isNotEmpty();
    }

    @Test
    void getTickets_shouldReturnAnEmptyListIfTicketsAreNotPresent() {

    // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        TicketRepository ticketRepository = mock(TicketRepository.class);
        ConcertRepository concertRepository = mock(ConcertRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);

        when(ticketRepository.getByOrderId(1L)).thenReturn(new ArrayList<>());

        TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, concertRepository);



    // Act
        List<Ticket> receivedTickets = sut.getTickets(1L);



    // Assert
        assertThat(receivedTickets).isEmpty();

    }

    @Test
    void updateTickets_shouldUpdateTicketInfo() throws Exception {

        // Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            TicketRepository ticketRepository = mock(TicketRepository.class);
            ConcertRepository concertRepository = mock(ConcertRepository.class);
            OrderRepository orderRepository = mock(OrderRepository.class);

            List<TicketEntity> ticketsToUpdate = Arrays.asList(new TicketEntity(1L, 1L, 1L,
                    "QR", "Hristo Ganchev", "Kim Petras", "TivoliVredenburg",
                    sdf.parse("2024/02/28"), "Utrecht", "Standing", null, null),
                    new TicketEntity(2L, 1L, 1L,
                    "QR", "Hristo Ganchev", "Kim Petras", "TivoliVredenburg",
                    sdf.parse("2024/02/28"), "Utrecht", "Standing", null, null));

            ConcertEntity concert = new ConcertEntity(1L, "Kim Petras",
                "Pop", "TivoliRonda", sdf.parse("2024/03/03"), "Utrecht",
                "Kim Petras is a German pop singer.", "URL", 40.15, 10000);


            List<TicketEntity> updatedTickets = Arrays.asList(new TicketEntity(1L, 1L, 1L,
                        "QR", "Hristo Ganchev", "Kim Petras", "TivoliRonda",
                        sdf.parse("2024/03/03"), "Utrecht", "Standing", null, null),
                new TicketEntity(2L, 1L, 1L,
                        "QR", "Hristo Ganchev", "Kim Petras", "TivoliRonda",
                        sdf.parse("2024/03/03"), "Utrecht", "Standing", null, null));

            UpdateTicketsRequest request = new UpdateTicketsRequest(1L);


            when(concertRepository.getById(1L)).thenReturn(concert);
            when(ticketRepository.getByConcertId(1L)).thenReturn(ticketsToUpdate);

            TicketsServiceImpl sut = new TicketsServiceImpl(ticketRepository, orderRepository, concertRepository);



        // Act
            sut.updateTickets(request);



        // Assert
            assertThat(ticketsToUpdate).isEqualTo(updatedTickets);
    }

}