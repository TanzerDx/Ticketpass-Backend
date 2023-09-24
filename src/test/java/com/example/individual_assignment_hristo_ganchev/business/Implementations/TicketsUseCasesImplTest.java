package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.TicketsRelated.UpdateTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.ConcertRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.TicketRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketsUseCasesImplTest {

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

        when(ticketRepository.getTickets(1L)).thenReturn(tickets);

        TicketsUseCasesImpl sut = new TicketsUseCasesImpl(ticketRepository, orderRepository, concertRepository);



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

        when(ticketRepository.getTickets(1L)).thenReturn(new ArrayList<>());

        TicketsUseCasesImpl sut = new TicketsUseCasesImpl(ticketRepository, orderRepository, concertRepository);



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
                "Kim Petras is a German pop singer.", "URL", 40.15, 10000L);


            List<Ticket> updatedTickets = Arrays.asList(new Ticket(1L, 1L, 1L,
                        "QR", "Hristo Ganchev", "Kim Petras", "TivoliRonda",
                        sdf.parse("2024/03/03"), "Utrecht", "Standing", null, null),
                new Ticket(2L, 1L, 1L,
                        "QR", "Hristo Ganchev", "Kim Petras", "TivoliRonda",
                        sdf.parse("2024/03/03"), "Utrecht", "Standing", null, null));

            UpdateTicketsRequest request = new UpdateTicketsRequest(1L);


            when(concertRepository.getConcert(1L)).thenReturn(concert);
            when(ticketRepository.getTicketsByConcert(1L)).thenReturn(ticketsToUpdate);

            TicketsUseCasesImpl sut = new TicketsUseCasesImpl(ticketRepository, orderRepository, concertRepository);



        // Act
            sut.updateTickets(request);



        // Assert
            assertThat(ticketsToUpdate).isEqualTo(updatedTickets);
    }

}