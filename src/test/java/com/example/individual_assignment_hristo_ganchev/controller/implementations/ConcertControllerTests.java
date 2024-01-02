package com.example.individual_assignment_hristo_ganchev.controller.implementations;

import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.LowerTicketNumberRequest;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.business.Interfaces.ConcertsService;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.ConcertRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ConcertControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConcertsService concertService;

    @MockBean
    private ConcertRepository concertRepository;

    @Test
    @WithMockUser(username = "testuser", roles = {"admin"})
    void addConcerts_shouldReturn201ResponseWithMethodBeingCalledCorrectly() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        AddConcertRequest request = new AddConcertRequest("Kim Petras",
                "Pop", "AFAS Live", "2024-03-03 19:00:00", "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);


        mockMvc.perform(post("/concerts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());


        verify(concertService, atLeastOnce()).addConcert(request);

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getConcerts_shouldReturn200ResponseWithConcertsArray() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        List<Concert> response = Arrays.asList(

                new Concert(1L, "Kim Petras",
                "Pop", "AFAS Live", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000),

                new Concert(2L, "Madison Beer",
                        "Pop", "AFAS Live", sdf.parse("2024-02-28T23:00:00.000+00:00"), "Amsterdam",
                        "Madison Beer is an American Pop Star that became popular in 2022.", "URL", 45.05, 5000)
        );


        when(concertService.getAllConcerts()).thenReturn(response);

        mockMvc.perform(get("/concerts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))


                //Kim Petras
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].artist").value("Kim Petras"))
                .andExpect(jsonPath("$[0].musicGenre").value("Pop"))
                .andExpect(jsonPath("$[0].venue").value("AFAS Live"))
                .andExpect(jsonPath("$[0].date").value("2024-02-27T23:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].city").value("Amsterdam"))
                .andExpect(jsonPath("$[0].description").value("Kim Petras is a German Pop Star that became popular in 2022."))
                .andExpect(jsonPath("$[0].photoURL").value("URL"))
                .andExpect(jsonPath("$[0].price").value(40.05))
                .andExpect(jsonPath("$[0].ticketsRemaining").value(5000))


                //Madison Beer
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].artist").value("Madison Beer"))
                .andExpect(jsonPath("$[1].musicGenre").value("Pop"))
                .andExpect(jsonPath("$[1].venue").value("AFAS Live"))
                .andExpect(jsonPath("$[1].date").value("2024-02-28T23:00:00.000+00:00"))
                .andExpect(jsonPath("$[1].city").value("Amsterdam"))
                .andExpect(jsonPath("$[1].description").value("Madison Beer is an American Pop Star that became popular in 2022."))
                .andExpect(jsonPath("$[1].photoURL").value("URL"))
                .andExpect(jsonPath("$[1].price").value(45.05))
                .andExpect(jsonPath("$[1].ticketsRemaining").value(5000));

        verify(concertService).getAllConcerts();

    }

    @Test
    void filterConcerts_shouldReturn200ResponseWithConcertsArray() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        List<Concert> response = Arrays.asList(

                new Concert(1L, "Kim Petras",
                        "Pop", "AFAS Live", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Amsterdam",
                        "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000),

                new Concert(2L, "Madison Beer",
                        "Pop", "AFAS Live", sdf.parse("2024-02-28T23:00:00.000+00:00"), "Amsterdam",
                        "Madison Beer is an American Pop Star that became popular in 2022.", "URL", 45.05, 5000)
        );

        List<ConcertEntity> toConvert = Arrays.asList(

                new ConcertEntity(1L, "Kim Petras",
                        "Pop", "AFAS Live", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Amsterdam",
                        "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000),

                new ConcertEntity(2L, "Madison Beer",
                        "Pop", "AFAS Live", sdf.parse("2024-02-28T23:00:00.000+00:00"), "Amsterdam",
                        "Madison Beer is an American Pop Star that became popular in 2022.", "URL", 45.05, 5000)
        );


        when(concertRepository.getByKeyword("Amsterdam")).thenReturn(toConvert);
        when(concertService.filterConcerts("Amsterdam")).thenReturn(response);

        mockMvc.perform(get("/concerts/filter")
                        .param("keyword", "Amsterdam"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))


                //Kim Petras
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].artist").value("Kim Petras"))
                .andExpect(jsonPath("$[0].musicGenre").value("Pop"))
                .andExpect(jsonPath("$[0].venue").value("AFAS Live"))
                .andExpect(jsonPath("$[0].date").value("2024-02-27T23:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].city").value("Amsterdam"))
                .andExpect(jsonPath("$[0].description").value("Kim Petras is a German Pop Star that became popular in 2022."))
                .andExpect(jsonPath("$[0].photoURL").value("URL"))
                .andExpect(jsonPath("$[0].price").value(40.05))
                .andExpect(jsonPath("$[0].ticketsRemaining").value(5000))


                //Madison Beer
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].artist").value("Madison Beer"))
                .andExpect(jsonPath("$[1].musicGenre").value("Pop"))
                .andExpect(jsonPath("$[1].venue").value("AFAS Live"))
                .andExpect(jsonPath("$[1].date").value("2024-02-28T23:00:00.000+00:00"))
                .andExpect(jsonPath("$[1].city").value("Amsterdam"))
                .andExpect(jsonPath("$[1].description").value("Madison Beer is an American Pop Star that became popular in 2022."))
                .andExpect(jsonPath("$[1].photoURL").value("URL"))
                .andExpect(jsonPath("$[1].price").value(45.05))
                .andExpect(jsonPath("$[1].ticketsRemaining").value(5000));

        verify(concertService).filterConcerts("Amsterdam");

    }

    @Test
    void lowerTicketNumber_shouldReturn204ResponseWithMethodBeingCalledCorrectly() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        LowerTicketNumberRequest request = new LowerTicketNumberRequest(1L, 3);

        ConcertEntity toReturn = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023-09-04T21:00"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 2);


        when(concertRepository.getById(1L)).thenReturn(toReturn);


        mockMvc.perform(put("/concerts/lowerTicketNumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());


        verify(concertService, atLeastOnce()).lowerTicketNumber(request);

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getConcerts_shouldReturn200ResponseWithAConcertOfID1() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Concert response = new Concert(1L, "Kim Petras",
                        "Pop", "AFAS Live", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Amsterdam",
                        "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);


        when(concertService.getConcert(1L)).thenReturn(response);

        mockMvc.perform(get("/concerts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.artist").value("Kim Petras"))
                .andExpect(jsonPath("$.musicGenre").value("Pop"))
                .andExpect(jsonPath("$.venue").value("AFAS Live"))
                .andExpect(jsonPath("$.date").value("2024-02-27T23:00:00.000+00:00"))
                .andExpect(jsonPath("$.city").value("Amsterdam"))
                .andExpect(jsonPath("$.description").value("Kim Petras is a German Pop Star that became popular in 2022."))
                .andExpect(jsonPath("$.photoURL").value("URL"))
                .andExpect(jsonPath("$.price").value(40.05))
                .andExpect(jsonPath("$.ticketsRemaining").value(5000));

        verify(concertService).getConcert(1L);

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void getConcerts_shouldReturn200ResponseWithEmptyArray() throws Exception {

        List<Concert> response = new ArrayList<>();


        when(concertService.getAllConcerts()).thenReturn(response);

        mockMvc.perform(get("/concerts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(concertService).getAllConcerts();

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"admin"})
    void updateConcerts_shouldReturn200ResponseWithMethodBeingCalledCorrectly() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        ConcertEntity toUpdate = new ConcertEntity(1L, "Kim Petras",
                "Pop", "AFAS Live", sdf.parse("2024-02-27T19:00:00.000+00:00"), "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);


        UpdateConcertRequest request = new UpdateConcertRequest(1L, "Kim Petras",
                        "Pop", "AFAS Live", "2024-03-03 19:00:00", "Amsterdam",
                        "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);



        when(concertRepository.getById(1L)).thenReturn(toUpdate);

        mockMvc.perform(put("/concerts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());


        verify(concertService, atLeastOnce()).updateConcert(request);

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void addConcerts_shouldThrow403Forbidden() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        AddConcertRequest request = new AddConcertRequest("Kim Petras",
                "Pop", "AFAS Live", "2024-03-03 19:00:00", "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);


        mockMvc.perform(post("/concerts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());


        verify(concertService, times(0)).addConcert(request);

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"user"})
    void updateConcerts_shouldThrow403Forbidden() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        ConcertEntity toUpdate = new ConcertEntity(1L, "Kim Petras",
                "Pop", "AFAS Live", sdf.parse("2024-02-27T19:00:00.000+00:00"), "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);


        UpdateConcertRequest request = new UpdateConcertRequest(1L, "Kim Petras",
                "Pop", "AFAS Live", "2024-03-03 19:00:00", "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 5000);



        when(concertRepository.getById(1L)).thenReturn(toUpdate);

        mockMvc.perform(put("/concerts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());


        verify(concertService, times(0)).updateConcert(request);

    }

}

