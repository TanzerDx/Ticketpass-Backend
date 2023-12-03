package com.example.individual_assignment_hristo_ganchev.controller.implementations;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketsService ticketsService;

    @MockBean
    private AccessToken accessToken;

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getTickets_shouldReturn200ResponseWithAListOfTickets() throws Exception {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T12:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        Order order = new Order(1L,  concert, user, sdf.parse("2024-02-27T12:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        List<Ticket> tickets = Arrays.asList(new Ticket(1L, order ,
                "QR", "Hristo Ganchev", "Standing", 1 , 1));


        when(accessToken.getUserId()).thenReturn(1l);
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
                .andExpect(jsonPath("$[0].venueRow").value(1))
                .andExpect(jsonPath("$[0].venueSeat").value(1));

        verify(ticketsService).getTickets(1L);
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getTickets_shouldReturn200ResponseWithAnEmptyList() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        List<Ticket> tickets = new ArrayList<>();

        when(accessToken.getUserId()).thenReturn(1l);
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
