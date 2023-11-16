package com.example.individual_assignment_hristo_ganchev.controller.implementations;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.ConcertsService;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.OrdersService;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.controller.ConcertController;
import com.example.individual_assignment_hristo_ganchev.controller.OrderController;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersService ordersService;


    @Test
    void getAllOrders_shouldReturn200ResponseWithAListOfOrders() throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com", null,
                "hashedPassword", false);


        List<Order> allOrders = Arrays.asList(new Order(1L,  concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal"));

        GetAllOrdersResponse response = GetAllOrdersResponse.builder()
                .orders(allOrders)
                .build();

        when(ordersService.getAllOrders(1L)).thenReturn(response);


        mockMvc.perform(get("/orders")
                .param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))


                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].concert").value(concert))
                .andExpect(jsonPath("$[0].user").value(user))
                .andExpect(jsonPath("$[0].date").value("2024-02-27T23:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].name").value("Hristo"))
                .andExpect(jsonPath("$[0].surname").value("Ganchev"))
                .andExpect(jsonPath("$[0].address").value("Woenselse Markt 18"))
                .andExpect(jsonPath("$[0].phone").value("+31613532345"))
                .andExpect(jsonPath("$[0].ticketNumber").value(3))
                .andExpect(jsonPath("$[0].orderPrice").value(14.15))
                .andExpect(jsonPath("$[0].paymentMethod").value("Ideal"));


        verify(ordersService).getAllOrders(1L);

    }


    @Test
    void getAllOrders_shouldReturn200ResponseWithAnEmptyList() throws Exception{

        GetAllOrdersResponse response = GetAllOrdersResponse.builder()
                .orders(new ArrayList<>())
                .build();

        when(ordersService.getAllOrders(1L)).thenReturn(response);

        mockMvc.perform(get("/orders")
                .param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));


        verify(ordersService).getAllOrders(1L);
    }


    @Test
    void getOrder_shouldReturn200ResponseWithAnOrderOfID1() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-28T00:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com", null,
                "hashedPassword", false);


        Order response =new Order(1L,  concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        when(ordersService.getOrder(1L)).thenReturn(response);


        mockMvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))


                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.concert").value(concert))
                .andExpect(jsonPath("$.user").value(user))
                .andExpect(jsonPath("$.date").value("2024-02-28T00:00:00.000+00:00"))
                .andExpect(jsonPath("$.name").value("Hristo"))
                .andExpect(jsonPath("$.surname").value("Ganchev"))
                .andExpect(jsonPath("$.address").value("Woenselse Markt 18"))
                .andExpect(jsonPath("$.phone").value("+31613532345"))
                .andExpect(jsonPath("$.ticketNumber").value(3))
                .andExpect(jsonPath("$.orderPrice").value(14.15))
                .andExpect(jsonPath("$.paymentMethod").value("Ideal"));


        verify(ordersService).getOrder(1L);

    }


}
