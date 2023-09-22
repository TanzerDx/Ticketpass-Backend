package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.GetConcertsResponse;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.Concert;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.ConcertRepository;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ConcertsUseCasesImplTest {

    @Test
    public void getConcert_shouldGetConcertById() throws Exception {

        // Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            ConcertRepository concertRepositoryMock = mock(ConcertRepository.class);

            ConcertEntity toTest = new ConcertEntity(1L, "Chase Atlantic",
                    "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                    "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000L);


            when(concertRepositoryMock.getConcert(1L)).thenReturn(toTest);

            ConcertsUseCasesImpl sut = new ConcertsUseCasesImpl(concertRepositoryMock);

        // Act
            Concert retrievedConcert = sut.getConcert(1L);


        // Assert
            assertThat(ConcertConverter.convert(toTest)).isEqualTo(retrievedConcert);
    }

    @Test
    public void getConcerts_shouldReturnEmptyListIfConcertsAreNotPresent() throws Exception {

        // Arrange
            ConcertRepository concertRepository = mock(ConcertRepository.class);
            when(concertRepository.getAll()).thenReturn(new ArrayList<ConcertEntity>());

            ConcertsUseCasesImpl sut = new ConcertsUseCasesImpl(concertRepository);


        // Act
            GetConcertsResponse sutResponse = sut.getAllConcerts();


        // Assert
            assertThat(sutResponse.getConcerts()).isEmpty();

    }


    @Test
    public void getConcerts_shouldReturnConcertsIfPresent() throws Exception {

        // Arrange
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            ConcertRepository concertRepository = mock(ConcertRepository.class);

            List<ConcertEntity> testConcerts = Arrays.asList(new ConcertEntity(1L, "Kim Petras",
                    "Pop", "AFAS Live", sdf.parse("2024/02/28"), "Amsterdam",
                    "Kim Petras is a German Pop Star that became popular in 2020.", "URL", 40.05, 1000L));
            when(concertRepository.getAll()).thenReturn(testConcerts);

            ConcertsUseCasesImpl sut = new ConcertsUseCasesImpl(concertRepository);

        // Act
            GetConcertsResponse sutResponse = sut.getAllConcerts();


        // Assert
            assertThat(sutResponse.getConcerts()).isNotEmpty();

    }
}
