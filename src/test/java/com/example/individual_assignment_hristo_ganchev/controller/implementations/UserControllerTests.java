package com.example.individual_assignment_hristo_ganchev.controller.implementations;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private AccessToken accessToken;

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getUser_shouldReturn200ResponseWithAUserOfID1() throws Exception  {

        User toReturn = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        when(accessToken.getUserId()).thenReturn(1L);
        when(usersService.getUserById(1L)).thenReturn(toReturn);


        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("hristo@gmail.com"))
                .andExpect(jsonPath("$.salt").value(""))
                .andExpect(jsonPath("$.hashedPassword").value("hashedPassword"))
                .andExpect(jsonPath("$.isAdmin").value(false));


        verify(usersService).getUserById(1L);
    }



}