package com.example.individual_assignment_hristo_ganchev.controller.implementations;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private UserRepository userRepository;

    @MockBean
    private AccessToken accessToken;

    @MockBean
    private PasswordEncoder passwordEncoder;


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
                .andExpect(jsonPath("$.encodedPassword").value("hashedPassword"))
                .andExpect(jsonPath("$.role").value("user"));


        verify(usersService).getUserById(1L);
    }


    @Test
    void Login_shouldReturn200ResponseWithLoggedInUser() throws Exception  {


        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        LoginRequest request = new LoginRequest("hristo@gmail.com",
                "hashedPassword");


        when(userRepository.login("hristo@gmail.com")).thenReturn(userEntity);
        when(passwordEncoder.matches(request.getPassword() , userEntity.getEncodedPassword())).thenReturn(true);

        mockMvc.perform(post("/users/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.accessToken").value("accessToken"));


        verify(usersService).Login(request);
    }


}
