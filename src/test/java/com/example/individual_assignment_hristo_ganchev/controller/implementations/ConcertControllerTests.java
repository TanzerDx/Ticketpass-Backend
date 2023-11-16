package com.example.individual_assignment_hristo_ganchev.controller.implementations;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.ConcertsService;
import com.example.individual_assignment_hristo_ganchev.controller.ConcertController;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConcertController.class)
public class ConcertControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcertsService concertService;


    @Test
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

}


// after implementing security in your project, you will probably need to switch from annotation
//
//@WebMvcTest(<Controller Name>.class)
//
//to
//
//@SpringBootTest
// @AutoConfigureMockMvc