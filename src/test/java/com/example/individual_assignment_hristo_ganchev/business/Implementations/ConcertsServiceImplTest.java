package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.business.ConcertsRelated.UpdateConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.jpa.ConcertRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ConcertsServiceImplTest {

    @Test
    public void getConcert_shouldGetConcertById() throws Exception {

        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ConcertRepository concertRepositoryMock = mock(ConcertRepository.class);

        ConcertEntity toReturn = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        Concert toCompare = new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        when(concertRepositoryMock.getById(1L)).thenReturn(toReturn);

        ConcertsServiceImpl sut = new ConcertsServiceImpl(concertRepositoryMock);



        // Act
        Concert retrievedConcert = sut.getConcert(1L);



        // Assert
        assertThat(toCompare).isEqualTo(retrievedConcert);
    }


    @Test
    public void getConcerts_shouldReturnEmptyListIfConcertsAreNotPresent() throws Exception {

        // Arrange
            ConcertRepository concertRepository = mock(ConcertRepository.class);
            when(concertRepository.findAll()).thenReturn(new ArrayList<ConcertEntity>());

            ConcertsServiceImpl sut = new ConcertsServiceImpl(concertRepository);



        // Act
            List<Concert> sutResponse = sut.getAllConcerts();



        // Assert
            assertThat(sutResponse).isEmpty();

    }


    @Test
    public void getConcerts_shouldReturnConcertsIfPresent() throws Exception {

        // Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            ConcertRepository concertRepository = mock(ConcertRepository.class);

            List<ConcertEntity> testConcerts = Arrays.asList(new ConcertEntity(1L, "Kim Petras",
                    "Pop", "AFAS Live", sdf.parse("2024/02/28"), "Amsterdam",
                    "Kim Petras is a German Pop Star that became popular in 2020.", "URL", 40.05, 1000));
            when(concertRepository.findAll()).thenReturn(testConcerts);

            ConcertsServiceImpl sut = new ConcertsServiceImpl(concertRepository);



        // Act
        List<Concert> sutResponse = sut.getAllConcerts();



        // Assert
        assertThat(sutResponse).isNotEmpty();
    }


    @Test
    public void updateConcert_shouldUpdateConcert() throws Exception {

        // Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            ConcertRepository concertRepositoryMock = mock(ConcertRepository.class);

            ConcertEntity toTest = new ConcertEntity(1L, "Chase Atlantic",
                    "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                    "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

            UpdateConcertRequest request = new UpdateConcertRequest(1L, "Chase Atlantic",
                    "Indie", "TivoliRonda", "2023/09/07", "Utrecht",
                    "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

            ConcertEntity updatedConcert = new ConcertEntity(1L, "Chase Atlantic",
                    "Indie", "TivoliRonda", sdf.parse("2023/09/07"), "Utrecht",
                    "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

            when(concertRepositoryMock.getById(1L)).thenReturn(toTest);

            ConcertsServiceImpl sut = new ConcertsServiceImpl(concertRepositoryMock);



        // Act
            sut.updateConcert(request);



        // Assert
            assertThat(toTest).isEqualTo(updatedConcert);
    }
}
