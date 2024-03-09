package com.example.individual_assignment_hristo_ganchev.controller.implementations;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.OrdersService;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.CreateOrderRequest;
import com.example.individual_assignment_hristo_ganchev.business.OrdersRelated.GetAllOrdersResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.OrderEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.OrderRepository;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private AccessToken accessToken;


    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void createOrder_shouldReturn201Response_withMethodBeingCalledCorrectly() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        CreateOrderRequest request = new CreateOrderRequest(concert, user, "2024-02-27T23:00:00.000+00:00", "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");


        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());


        verify(ordersService, atLeastOnce()).createOrder(request);
    }


    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getAllOrders_shouldReturn200Response_withAListOfOrders() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        List<Order> allOrders = Arrays.asList(new Order(1L,  concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal"));

        GetAllOrdersResponse response = GetAllOrdersResponse.builder()
                .orders(allOrders)
                .build();

        when(accessToken.getUserId()).thenReturn(1L);
        when(ordersService.getAllOrders(1L)).thenReturn(response);


        mockMvc.perform(get("/orders")
                        .param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.orders").isArray())
                .andExpect(jsonPath("$.orders", hasSize(1)))
                .andExpect(jsonPath("$.orders[0].id").value(1))
                .andExpect(jsonPath("$.orders[0].concert").value(concert))
                .andExpect(jsonPath("$.orders[0].user").value(user))
                .andExpect(jsonPath("$.orders[0].date").value("2024-02-27T23:00:00.000+00:00"))
                .andExpect(jsonPath("$.orders[0].name").value("Hristo"))
                .andExpect(jsonPath("$.orders[0].surname").value("Ganchev"))
                .andExpect(jsonPath("$.orders[0].address").value("Woenselse Markt 18"))
                .andExpect(jsonPath("$.orders[0].phone").value("+31613532345"))
                .andExpect(jsonPath("$.orders[0].ticketNumber").value(3))
                .andExpect(jsonPath("$.orders[0].orderPrice").value(14.15))
                .andExpect(jsonPath("$.orders[0].paymentMethod").value("Ideal"));


        verify(ordersService).getAllOrders(1L);

    }


    @Test
    @WithMockUser(username = "testuser", roles = {"admin"})
    void getOrdersForAllUsers_shouldReturn200Response_withAListOfOrders() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        UserEntity userEntity2 = new UserEntity(2L, "nikol@gmail.com",
                "hashedPassword", "user");



        Concert concert= new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        User user2 = new User(2L, "nikol@gmail.com",
                "hashedPassword", "user");


        List<OrderEntity> allOrders = Arrays.asList(
                new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                        "+31613532345", 3, 14.15, "Ideal"),

                new OrderEntity(2L,  concertEntity, userEntity2, sdf.parse("2024-02-27T21:00:00.000+00:00"), "Nikol", "Genova", "Woenselse Markt 11",
                        "+31613532341", 2, 8.10, "PayPal"));

        List<Order> toReturn = Arrays.asList(
                new Order(1L,  concert, user, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                        "+31613532345", 3, 14.15, "Ideal"),

                new Order(2L,  concert, user2, sdf.parse("2024-02-27T21:00:00.000+00:00"), "Nikol", "Genova", "Woenselse Markt 11",
                        "+31613532341", 2, 8.10, "PayPal"));


        when(orderRepository.findAll()).thenReturn(allOrders);
        when(ordersService.getOrdersForAllUsers()).thenReturn(toReturn);

        mockMvc.perform(get("/orders/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))


                // Orders 1
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
                .andExpect(jsonPath("$[0].paymentMethod").value("Ideal"))


                // Order 2
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].concert").value(concert))
                .andExpect(jsonPath("$[1].user").value(user2))
                .andExpect(jsonPath("$[1].date").value("2024-02-27T21:00:00.000+00:00"))
                .andExpect(jsonPath("$[1].name").value("Nikol"))
                .andExpect(jsonPath("$[1].surname").value("Genova"))
                .andExpect(jsonPath("$[1].address").value("Woenselse Markt 11"))
                .andExpect(jsonPath("$[1].phone").value("+31613532341"))
                .andExpect(jsonPath("$[1].ticketNumber").value(2))
                .andExpect(jsonPath("$[1].orderPrice").value(8.10))
                .andExpect(jsonPath("$[1].paymentMethod").value("PayPal"));


        verify(ordersService).getOrdersForAllUsers();

    }


    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getOrder_shouldReturn200Response_withAnOrderOfID1() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert concert = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-28T00:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");


        Order response =new Order(1L,  concert, user, sdf.parse("2024-02-28T00:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                "+31613532345", 3, 14.15, "Ideal");

        when(accessToken.getUserId()).thenReturn(1L);
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

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getOrdersForAllUsers_shouldThrow403Forbidden_whenUserIsUnauthorized() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        ConcertEntity concertEntity = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        UserEntity userEntity2 = new UserEntity(2L, "nikol@gmail.com",
                "hashedPassword", "user");



        List<OrderEntity> allOrders = Arrays.asList(
                new OrderEntity(1L,  concertEntity, userEntity, sdf.parse("2024-02-27T23:00:00.000+00:00"), "Hristo", "Ganchev", "Woenselse Markt 18",
                        "+31613532345", 3, 14.15, "Ideal"),

                new OrderEntity(2L,  concertEntity, userEntity2, sdf.parse("2024-02-27T21:00:00.000+00:00"), "Nikol", "Genova", "Woenselse Markt 11",
                        "+31613532341", 2, 8.10, "PayPal"));


        when(orderRepository.findAll()).thenReturn(allOrders);


        mockMvc.perform(get("/orders/all"))
                .andDo(print())
                .andExpect(status().isForbidden());


        verify(ordersService, times(0)).getOrdersForAllUsers();

    }


}