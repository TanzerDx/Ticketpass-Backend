package com.example.individual_assignment_hristo_ganchev.business.converters;

import com.example.individual_assignment_hristo_ganchev.business.Converters.ConcertConverter;
import com.example.individual_assignment_hristo_ganchev.domain.Concert;
import com.example.individual_assignment_hristo_ganchev.persistence.entities.ConcertEntity;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ConcertConverterTests {

    @Test
    public void convert_shouldConvertTheConcertEntity_toConcert() throws Exception
    {
        //Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ConcertConverter concertConverterMock = mock(ConcertConverter.class);

        ConcertEntity toConvert= new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        Concert toCompare= new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);



        //Act
        Concert returnedConcert = concertConverterMock.convert(toConvert);



        //Assert
        assertThat(toCompare).isEqualTo(returnedConcert);

    }

    @Test
    public void convertToEntity_shouldConvertTheConcert_toConcertEntity() throws Exception
    {
        //Arrange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ConcertConverter concertConverterMock = mock(ConcertConverter.class);

        Concert toConvert= new Concert(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);

        ConcertEntity toCompare= new ConcertEntity(1L, "Chase Atlantic",
                "Indie", "TivoliVredenburg", sdf.parse("2023/09/04"), "Utrecht",
                "Chase Atlantic are an Australian Indie band that became popular in 2015", "URL", 37.15, 1000);



        //Act
        ConcertEntity returnedConcert = concertConverterMock.convertToEntity(toConvert);



        //Assert
        assertThat(toCompare).isEqualTo(returnedConcert);

    }

}
