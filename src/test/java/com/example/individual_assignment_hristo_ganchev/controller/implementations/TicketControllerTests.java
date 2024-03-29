package com.example.individual_assignment_hristo_ganchev.controller.implementations;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.TicketsService;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.Ticket;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketsService ticketsService;

    @MockBean
    private AccessToken accessToken;

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void addTickets_shouldReturn201Response_withMethodBeingCalledCorrectly() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        Order order = new Order(1L, concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        AddTicketsRequest request = new AddTicketsRequest(order);


        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());


        verify(ticketsService, atLeastOnce()).addTickets(request);
    }


    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getTickets_shouldReturn200Response_withAnEmptyList() throws Exception {
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
