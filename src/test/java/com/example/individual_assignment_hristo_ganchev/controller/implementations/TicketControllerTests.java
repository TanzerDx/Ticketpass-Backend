package com.example.individual_assignment_hristo_ganchev.controller.implementations;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.controller.TicketController;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.TicketEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketsService ticketsService;

    @Test
    void getTickets_shouldReturn200ResponseWithAListOfTickets() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com", null,
                "hashedPassword", false);


        Order order = new Order(1L,  concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        List<Ticket> tickets = Arrays.asList(new Ticket(1L, order ,
                "QR", "Hristo Ganchev", "Standing", null, null));


        when(ticketsService.getTickets(1L)).thenReturn(tickets);

        mockMvc.perform(get("/tickets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))


                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].order").value(order))
                .andExpect(jsonPath("$[0].QR").value("QR"))
                .andExpect(jsonPath("$[0].userName").value("Hristo Ganchev"))
                .andExpect(jsonPath("$[0].venueSection").value("Standing"))
                .andExpect(jsonPath("$[0].venueRow").value(""))
                .andExpect(jsonPath("$[0].venueSeat").value(""));

        verify(ticketsService).getTickets(1L);
    }

    @Test
    void getTickets_shouldReturn200ResponseWithAnEmptyList() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        List<Ticket> tickets = new ArrayList<>();

        when(ticketsService.getTickets(1L)).thenReturn(tickets);

        mockMvc.perform(get("/tickets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(ticketsService).getTickets(1L);

    }


}
