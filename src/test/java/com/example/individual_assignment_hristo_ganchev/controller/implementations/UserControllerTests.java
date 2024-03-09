package com.example.individual_assignment_hristo_ganchev.controller.implementations;


import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.business.TicketsRelated.AddTicketsRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.domain.Order;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.UserEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.UserRepository;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessToken;
import com.example.individual_assignment_hristo_ganchev.security.token.AccessTokenDecoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AccessToken accessToken;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;


    @MockBean
    private PasswordEncoder passwordEncoder;

    private String asJsonString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    @Test
    void addUser_shouldReturn201Response_withMethodBeingCalledCorrectly() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        AddUserRequest request = new AddUserRequest("hristo@gmail.com",
                "hashedPassword");


        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());


        verify(usersService, atLeastOnce()).addUser(request, "user");
    }

    @Test
    @WithMockUser(username = "test", roles = {"manager"})
    void addAdmin_shouldReturn201Response_withMethodBeingCalledCorrectly() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        AddUserRequest request = new AddUserRequest("hristo@gmail.com",
                "hashedPassword");


        mockMvc.perform(post("/users/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());


        verify(usersService, atLeastOnce()).addUser(request, "admin");
    }

    @Test
    @WithMockUser(username = "test", roles = {"user"})
    void getUser_shouldReturn200Response_withAUserOfID1() throws Exception  {

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
    @WithMockUser(username = "test", roles = {"manager"})
    void deleteAdmin_shouldReturn204Response_withMethodBeingCalledCorrectly() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        mockMvc.perform(delete("/users/admin/1"))
                .andExpect(status().isNoContent());


        verify(usersService, atLeastOnce()).deleteAdmin(1L);
    }

    @Test
    @WithMockUser(username = "test", roles = {"manager"})
    void banUser_shouldReturn200Response_withUserStatusBanned() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "banned");

        when(userRepository.getById(1L)).thenReturn(userEntity);
        when(usersService.banUser(1L)).thenReturn(user);

        mockMvc.perform(put("/users/ban/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("hristo@gmail.com"))
                .andExpect(jsonPath("$.encodedPassword").value("hashedPassword"))
                .andExpect(jsonPath("$.role").value("banned"));


        verify(usersService).banUser(1L);
    }

    @Test
    @WithMockUser(username = "test", roles = {"manager"})
    void unbanUser_shouldReturn200Response_withUserStatusUser() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "banned");

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        when(userRepository.getById(1L)).thenReturn(userEntity);
        when(usersService.unbanUser(1L)).thenReturn(user);

        mockMvc.perform(put("/users/unban/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("hristo@gmail.com"))
                .andExpect(jsonPath("$.encodedPassword").value("hashedPassword"))
                .andExpect(jsonPath("$.role").value("user"));


        verify(usersService).unbanUser(1L);
    }

    @Test
    @WithMockUser(username = "test", roles = {"user"})
    void deleteAdmin_shouldThrow403Forbidden_whenUserIsUnauthorized() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        mockMvc.perform(delete("/users/admin/1"))
                .andExpect(status().isForbidden());


        verify(usersService, times(0)).deleteAdmin(1L);
    }

    @Test
    @WithMockUser(username = "test", roles = {"user"})
    void banUser_shouldThrow405NotAllowed_whenUserIsUnauthorized() throws Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        UserEntity userEntity = new UserEntity(1L, "hristo@gmail.com",
                "hashedPassword", "user");

        User user = new User(1L, "hristo@gmail.com",
                "hashedPassword", "banned");

        when(userRepository.getById(1L)).thenReturn(userEntity);
        when(usersService.banUser(1L)).thenReturn(user);

        mockMvc.perform(put("/users/1"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());


        verify(usersService, times(0)).banUser(1L);
    }

}
