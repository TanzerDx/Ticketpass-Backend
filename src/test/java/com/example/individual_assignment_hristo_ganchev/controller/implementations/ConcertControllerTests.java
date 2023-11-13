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

        List<Concert> response = Arrays.asList(new Concert(1L, "Kim Petras",
                "Pop", "AFAS Live", sdf.parse("2024-02-27T23:00:00.000+00:00"), "Amsterdam",
                "Kim Petras is a German Pop Star that became popular in 2022.", "URL", 40.05, 1000));

        when(concertService.getAllConcerts()).thenReturn(response);

        mockMvc.perform(get("/concerts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type",
                        APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").value("""            
                    {
                       "concerts": [
                         {
                           "id": "1",
                           "artist": "Kim Petras",
                           "musicGenre": "Pop",
                           "venue": "AFAS Live",
                           "date": "2024-02-27T23:00:00.000+00:00",
                           "city": "Amsterdam",
                           "description": "Kim Petras is a German Pop Star that became popular in 2022.",
                           "photoURL": "URL",
                           "price": 40.05,
                           "ticketsRemaining": 1000
                         }
                       ]
                     }
                        """));

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