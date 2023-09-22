package com.example.individual_assignment_hristo_ganchev.business.Implementations;

import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.AddConcertRequest;
import com.example.individual_assignment_hristo_ganchev.domain.ConcertsRelated.GetConcertsResponse;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import com.example.individual_assignment_hristo_ganchev.persistence.interfaces.ConcertRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ConcertsUseCasesImplTest {

    @Test
    public void addConcert_shouldAddAConcert() throws Exception {

        // Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ConcertRepository concertRepositoryMock = mock(ConcertRepository.class);

        ConcertEntity toTest = new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000L);

        AddConcertRequest toAdd = new AddConcertRequest("Chase Atlantic",
                        "Indie", "TivoliVredenburg", "2023/09/04", "Utrecht",
                        "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000L);

        when(concertRepositoryMock.addConcert(toTest)).thenReturn(toTest);

        ConcertsUseCasesImpl sut = new ConcertsUseCasesImpl(concertRepositoryMock);

        // Act
        sut.addConcert(toAdd);
        //Concert retrievedConcert = sut.getConcert(1L);


        // Assert
        //assertThat(ConcertConverter.convert(toTest)).isEqualTo(retrievedConcert);
        Mockito.verify(concertRepositoryMock.addConcert(Mockito.argThat(
                new ArgumentMatcher<ConcertEntity>() {
                    @Override
                    public boolean matches(ConcertEntity argument) {
                        return toTest.getId().equals(argument.getId()) &&
                                toTest.getArtist().equals(argument.getArtist()) &&
                                toTest.getMusicGenre().equals(argument.getMusicGenre()) &&
                                toTest.getVenue().equals(argument.getVenue()) &&
                                toTest.getDate().equals(argument.getDate()) &&
                                toTest.getCity().equals(argument.getCity()) &&
                                toTest.getDesc().equals(argument.getDesc()) &&
                                toTest.getPhotoURL().equals(argument.getPhotoURL()) &&
                                toTest.getPrice().equals(argument.getPrice()) &&
                                toTest.getTicketsRemaining().equals(argument.getTicketsRemaining());
                    }
                }
        )));

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
